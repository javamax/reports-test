package ru.reports.server.web.controller;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by maxim on 31.01.14.
 */
public class TestParsing extends TestCase {

    public void testParse() throws Exception {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("data.txt"));
        List data = new ArrayList(300);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            System.out.println(s);

            if (s == null || s.length() < 3) continue;

            String[] elem = s.substring(1, s.length() - 1).split(",");
            data.add(elem);
            //System.out.println(Arrays.toString(elem));
        }
    }
}
