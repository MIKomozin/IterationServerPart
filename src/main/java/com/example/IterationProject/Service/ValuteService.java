package com.example.IterationProject.Service;

import com.example.IterationProject.DataFromXML.DataValute;
import com.example.IterationProject.Entity.ConvertionHistory;
import com.example.IterationProject.Entity.Valute;
import com.example.IterationProject.Entity.ValuteCourse;
import com.example.IterationProject.Repository.ConvertionHistoryRepository;
import com.example.IterationProject.Repository.ValuteCourseRepository;
import com.example.IterationProject.Repository.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ValuteService {

    private final ValuteRepository valuteRepository;
    private final ValuteCourseRepository valuteCourseRepository;
    private final ConvertionHistoryRepository convertionHistoryRepository;
    private final LoadCourseOfValute loadCourseOfValute;

    @Autowired
    public ValuteService(ValuteRepository valuteRepository,
                         ValuteCourseRepository valuteCourseRepository,
                         ConvertionHistoryRepository convertionHistoryRepository,
                         LoadCourseOfValute loadCourseOfValute) {
        this.valuteRepository = valuteRepository;
        this.valuteCourseRepository = valuteCourseRepository;
        this.convertionHistoryRepository = convertionHistoryRepository;
        this.loadCourseOfValute = loadCourseOfValute;
    }

    //метод добавления данных с сайта в БД при загрузке приложения
    public void addDataInDataBaseBeforeStartApp() {
        List<DataValute> dataValuteList = loadCourseOfValute.getCourseOfValutes().getValute();
        String data_str = loadCourseOfValute.getCourseOfValutes().getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(data_str, formatter);

        for (DataValute dataValute : dataValuteList) {
            Valute valute = new Valute();
            valute.setName(dataValute.getName());
            valute.setNumCode(dataValute.getNumCode());
            valute.setCharCode(dataValute.getCharCode());
            //проверка на существование валюты в БД
            if (getValuteByName(dataValute.getName()) == null) {
                valuteRepository.save(valute);
            }

            //проверка на существование курса валюты на данную дату в БД
            if (getValuteCourseByValuteAndDate(valute, date) == null) {
                ValuteCourse valuteCourse = new ValuteCourse();
                valuteCourse.setValute(valute);
                valuteCourse.setNominal(dataValute.getNominal());
                valuteCourse.setValue(Double.parseDouble(dataValute.getValue().replace(",",".")));
                valuteCourse.setDate(date);
                valuteCourseRepository.save(valuteCourse);
            }
        }
    }

    //метод добавления данных по курсу валют с сайта в БД на текущую дату
    public boolean addDataInDataBaseWithDate(LocalDate date) {
        List<DataValute> dataValuteList = loadCourseOfValute.getCourseOfValutesByDate(date).getValute();
        if (dataValuteList == null) {
            return false;
        } else {
            for (DataValute dataValute : dataValuteList) {
                ValuteCourse valuteCourse = new ValuteCourse();
                valuteCourse.setValute(valuteRepository.findValuteByName(dataValute.getName()));
                valuteCourse.setNominal(dataValute.getNominal());
                valuteCourse.setValue(Double.parseDouble(dataValute.getValue().replace(",", ".")));
                valuteCourse.setDate(date);
                valuteCourseRepository.save(valuteCourse);
            }
            return true;
        }
    }

    //метода для конвертирования валют, где inputCharCode charCode входной валюты, inputData - ее количестов
    //outputCharCode - charCode необходимой нам валюты
    public Double getConverterValute(String inputCharCode, String outputCharCode, double inputData) {
        boolean addActualData = false;
        LocalDate date = LocalDate.now();
        //если курса валют на данную дату нет, то добавляем его
        if (getValuteCoursesByDate(date) == null) {
            addActualData = addDataInDataBaseWithDate(date);
        }
        //проверяем добавили имеется ла в ЦБ курс валют на текущую дату и если нет, то конвертируем по крайней дате
        if (!addActualData) {
            date = valuteCourseRepository.findValuteCourseWithActualDate().getDate();
        }
        double result = 0.0;
        ValuteCourse inputValute = valuteCourseRepository.findValuteCourseByCharCodeAndDate(inputCharCode, date);
        ValuteCourse outputValute = valuteCourseRepository.findValuteCourseByCharCodeAndDate(outputCharCode, date);
        if (inputValute != null && outputValute != null) {
            double inValue = inputValute.getValue();
            double outValue = outputValute.getValue();
            int inNominal = inputValute.getNominal();
            int outNominal = outputValute.getNominal();
            //соотношение валют друг к другу
            double relation = (inValue * outNominal) / (outValue * inNominal);
            //необходимый нам результат как произведение отношения валют на количество имеющейся у нас валюты
            result = inputData * relation;
            //дабавляем данную операцию в историю
            ConvertionHistory convertionHistory = new ConvertionHistory();
            convertionHistory.setHaveValute(inputCharCode);
            convertionHistory.setWantValute(outputCharCode);
            convertionHistory.setHaveValue(inputData);
            convertionHistory.setWantValue(result);
            convertionHistory.setCourse(relation);
            convertionHistory.setDate(date);
            convertionHistoryRepository.save(convertionHistory);
        }
        return result;
    }

    //метод ля подсчета среднего курса конвертации указанных пар валют за неделю
    public Double getAvgCourse(String inputCharCode, String outputCharCode) {
        return convertionHistoryRepository.findAvgCourse(inputCharCode, outputCharCode);
    }

    //метод для подсчета суммарнго объёма конвертаций по каждой паре за неделю
    public Integer getNumberOfConvertion(String inputCharCode, String outputCharCode) {
        return convertionHistoryRepository.findNumberOfConvertion(inputCharCode, outputCharCode);
    }

    //метод для просмотра истории конвертации для указанных пар валют
    public List<ConvertionHistory> getHistoryOfConvertion(String inputCharCode, String outputCharCode) {
        return convertionHistoryRepository.findHistoryOfConvertion(inputCharCode, outputCharCode);
    }

    public Valute getValuteByName(String name) {
        return valuteRepository.findValuteByName(name);
    }

    public ValuteCourse getValuteCourseByValuteAndDate(Valute valute, LocalDate date) {
        return valuteCourseRepository.findValuteCourseByValuteAndDate(valute, date);
    }

    //метод проверки кураса валют на определенную дату
    public List<ValuteCourse> getValuteCoursesByDate(LocalDate date) {
        return valuteCourseRepository.findValuteCoursesByDate(date);
    }
}
