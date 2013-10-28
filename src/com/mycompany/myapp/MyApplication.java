package com.mycompany.myapp;

import ca.weblite.codename1.components.charts.Chart;
import ca.weblite.codename1.components.charts.ChartBuilder;
import ca.weblite.codename1.components.charts.ChartView;
import ca.weblite.codename1.components.charts.Series;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class MyApplication {

    private Form current;
    private ChartView chartView;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }
        final Form hi = new Form("Bar Chart");
        hi.setLayout(new BorderLayout());
        ChartBuilder b = new ChartBuilder();
        final Chart chart = b.newBarChart(
                new double[][]{
                    {1, 3, 2, 5},
                    {3, 1, 2, 4},
                    {7, 4, 1, 6},
                    {2, 3, 4, 1}
                },
                new String[]{"BC", "Alberta", "Ontario", "Saskatchewan"},
                new String[]{"June", "July", "August", "Sept"}
        );
        
        final Chart chart2 = b.newBarChart(
                new double[][]{
                    {5, 1, 3, 2},
                    {3, 9, 7, 2},
                    {6, 4, 2, 2},
                    {2, 3, 4, 4}
                },
                new String[]{"NWT", "Quebec", "Manitoba", "Nova Scotia"},
                new String[]{"June", "July", "August", "Sept"}
        );
        
        chartView = new ChartView(chart);
        chartView.initLater();
        hi.addComponent(BorderLayout.CENTER, chartView);
        hi.show();
        
        hi.addOrientationListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                if ( chartView.getChartModel() == chart ){
                    chartView.setChartModel(chart2);
                } else {
                    chartView.setChartModel(chart);
                }
                chartView.update();
                
            }
            
        });
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }

}
