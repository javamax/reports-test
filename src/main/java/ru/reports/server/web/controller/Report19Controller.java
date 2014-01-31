package ru.reports.server.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.reports.server.cache.DataHolder;

import java.util.*;

/**
 * @author Kirill Mukhov
 */
@Controller
@RequestMapping("/report19")
public class Report19Controller {

    protected static final Logger logger = LoggerFactory.getLogger(Report19Controller.class);

    public static final int MAX_LIMIT = 1000;
    public static final String LIMIT_DEFVALUE = MAX_LIMIT + "";

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> list(@RequestParam(required = false) Set<Byte> statuses,
                           @RequestParam(required = false) Date startDate,
                           @RequestParam(required = false) Date endDate,
                           @RequestParam(required = false, defaultValue = LIMIT_DEFVALUE) Long limit,
                           @RequestParam(required = false, defaultValue = "0") Long offset) {
        List<String[]> data = DataHolder.getInstance().getData();
        List<Map<String, Object>> result = new ArrayList<>();
        for (String[] row : data) {
            Map<String, Object> item = new LinkedHashMap<>(row.length);
            result.add(item);
            for (int i = 1; i < row.length; i++) {
                item.put(Data.PARAM_NAMES[i], row[i]);
            }
        }

        return result;
    }

    @RequestMapping(value = "/data2", method = RequestMethod.GET)
    @ResponseBody
    public List<Report> data2() {
        List<Report> reports = Arrays.asList(new Report(1L, "Test", new Date()), new Report(23L, "Tasks", new Date(13700120)));

        return reports;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> data() {
        List<Map<String, Object>> data = new ArrayList<>();
        for (Object[] row : Data.val) {
            Map<String, Object> item = new LinkedHashMap<>(row.length);
            data.add(item);
            for (int i = 0; i < row.length; i++) {
                item.put(Data.PARAM_NAMES[i], row[i]);
            }
        }

        return data;
    }

    public static class Report {
        private Long id;
        private String name;
        private Date date;

        public Report(Long id, String name, Date date) {
            this.id = id;
            this.name = name;
            this.date = date;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Report{");
            sb.append("id=").append(id);
            sb.append(", name='").append(name).append('\'');
            sb.append(", date=").append(date);
            sb.append('}');
            return sb.toString();
        }
    }
}
