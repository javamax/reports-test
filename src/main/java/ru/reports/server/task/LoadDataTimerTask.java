package ru.reports.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.reports.server.cache.DataHolder;

import java.util.*;

/**
 * Created by maxim on 31.01.14.
 */
public class LoadDataTimerTask extends TimerTask {

    private static final Logger logger = LoggerFactory.getLogger(LoadDataTimerTask.class);

    @Override
    public void run() {
        logger.info("Start Load Data");

        List<String[]> data = parseData();
        DataHolder.getInstance().setData(data);

        logger.info("Loaded {} data items", data.size());
        if (logger.isDebugEnabled())
            logger.debug("Data: {}", dataToString(data));

        logger.info("End Load Data");
    }

    public List<String[]> parseData() {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("data.txt"));
        List<String[]> data = new ArrayList<>(300);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            //System.out.println(s);

            if (s == null || s.length() < 3) continue;

            String[] row = s.substring(1, s.length() - 1).split(",");
            for (int i = 0; i < row.length; i++) {
                String field = row[i];
                field = field.trim();
                if (field.isEmpty()) continue;

                if (field.indexOf('"') == 0) field = field.substring(1, field.length() - 1);
                row[i] = field;
            }

            data.add(row);
            //System.out.println(Arrays.toString(elem));
        }

        return data;
    }

    private String dataToString(List<String[]> data) {
        StringBuilder sb = new StringBuilder("{");
        for (String[] strings : data) {
            sb.append(Arrays.toString(strings)).append(",\n ");
        }

        sb.append("}");

        return sb.toString();
    }
}
