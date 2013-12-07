package com.mycompany.myapp;

import ca.weblite.codename1.components.charts.Chart;
import ca.weblite.codename1.components.charts.ChartBuilder;
import ca.weblite.codename1.components.charts.ChartView;
import ca.weblite.codename1.components.charts.GridOptions;
import ca.weblite.codename1.components.charts.Options;
import ca.weblite.codename1.components.charts.Series;
import ca.weblite.codename1.components.charts.event.ChartEvent;
import ca.weblite.codename1.components.charts.event.ChartListener;
import com.codename1.components.WebBrowser;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
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
        showMainMenu(false);

    }

    private void showMainMenu(boolean back) {
        Form hi = new Form("CN1 Charts");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Button barchartsBtn = new Button("Bar Charts");
        barchartsBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().callSerially(new Runnable() {

                    public void run() {
                        showBarCharts();
                    }

                });

            }

        });

        Button piechartsBtn = new Button("Pie Charts");
        piechartsBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().callSerially(new Runnable() {

                    public void run() {
                        showPieCharts();
                    }

                });
            }

        });
        
        Button otherChartBtn = new Button("Other Chart");
        otherChartBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().callSerially(new Runnable() {

                    public void run() {
                        showChart();
                    }

                });
            }

        });
        
        Button flotExampleBtn = new Button("Flot Example");
        flotExampleBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().callSerially(new Runnable() {

                    public void run() {
                        showFlotExample();
                    }

                });
            }

        });
        
        Button jsonBtn = new Button("JSON Chart Example");
        jsonBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().callSerially(new Runnable(){

                    public void run() {
                        showJSONCharts();
                    }
                    
                });
            }
            
        });
        
        Button interactivityBtn = new Button("Interactivity Example");
        interactivityBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().callSerially(new Runnable(){

                    public void run() {
                        interactivityExample();
                    }
                    
                });
            }
            
        });
        
        hi.addComponent(barchartsBtn);
        hi.addComponent(piechartsBtn);
        hi.addComponent(otherChartBtn);
        hi.addComponent(flotExampleBtn);
        hi.addComponent(jsonBtn);
        hi.addComponent(interactivityBtn);
        if (back) {
            hi.showBack();
        } else {
            hi.show();
        }

    }

    private void showPieCharts() {
        final Form hi = new Form("Pie Chart");
        hi.setBackCommand(new Command("Main Menu") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showMainMenu(true);
            }

        });
        hi.setLayout(new BorderLayout());
        ChartBuilder b = new ChartBuilder();
        final Chart chart = b.newPieChart(new double[]{15, 25, 60}, new String[]{"Facebook", "Twitter", "LinkedIn"});
        chartView = new ChartView(chart);
        chartView.initLater();
        hi.addComponent(BorderLayout.CENTER, chartView);
        hi.show();

    }
    
    private void interactivityExample() {
        final Form hi = new Form("Pie Chart");
        hi.setBackCommand(new Command("Main Menu") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showMainMenu(true);
            }

        });
        hi.setLayout(new BorderLayout());
        ChartBuilder b = new ChartBuilder();
        final Chart chart = b.newPieChart(new double[]{15, 25, 60}, new String[]{"Facebook", "Twitter", "LinkedIn"});
        //Options opts = new Options();
        GridOptions gopts = new GridOptions().clickable(true);
        chart.options().grid(gopts);
        //chart.options(opts);
        
        //chart.options().grid().clickable(true);
        chartView = new ChartView(chart);
        chartView.addChartListener(new ChartListener(){

            public void plotClicked(ChartEvent evt) {
                Log.p("The plot was clicked");
                Log.p(evt.getSeries().label());
                Log.p(evt.getSeries().color().stringVal());
                Log.p(evt.getDataPoint().toString());
                Log.p(evt.getSeriesIndex()+"");
                
                Dialog.show("Clicked "+evt.getSeries().label(), "Color: "+evt.getSeries().color().stringVal(), "OK", "Cancel");
                
            }

            public void plotHovered(ChartEvent evt) {
                Log.p("Hover event");
            }
            
        });
        chartView.initLater();
        hi.addComponent(BorderLayout.CENTER, chartView);
        hi.show();

    }
    
    private void showJSONCharts() {
        final Form hi = new Form("Pie Chart");
        hi.setBackCommand(new Command("Main Menu") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showMainMenu(true);
            }

        });
        hi.setLayout(new BorderLayout());
        String d2 = "[[0, 3], [4, 8], [8, 5], [9, 13]]";
        String d3 = "[[0, 12], [7, 12], null, [7, 2.5], [12, 2.5]]";
        String config = "["+d2+","+d3+"]";
        chartView = new ChartView(config, "undefined");
        chartView.initLater();
        hi.addComponent(BorderLayout.CENTER, chartView);
        hi.show();

    }

    private void showBarCharts() {
        final Form hi = new Form("Bar Chart");
        hi.setBackCommand(new Command("Main Menu") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showMainMenu(true);
            }

        });
        hi.setLayout(new BorderLayout());
        new Thread(new Runnable() {

            public void run() {
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
                Display.getInstance().callSerially(new Runnable() {

                    public void run() {
                        chartView = new ChartView(chart);
                        chartView.initLater();
                        hi.addComponent(BorderLayout.CENTER, chartView);
                        hi.show();

                        hi.addOrientationListener(new ActionListener() {

                            public void actionPerformed(ActionEvent evt) {
                                if (chartView.getChartModel() == chart) {
                                    chartView.setChartModel(chart2);
                                } else {
                                    chartView.setChartModel(chart);
                                }
                                chartView.update();

                            }

                        });
                    }

                });

            }

        }).start();

    }

    private void showChart() {
        Form f = new Form();
        final Label l = new Label("Loading Chart....pls wait");
        f.setLayout(new BorderLayout());
        Log.p("About to create builder");
        ChartBuilder b = new ChartBuilder();
        Log.p("Builder created");
        
        Chart chart = b.newBarChart(
                new double[][]{
                    {1, 3, 2, 5},
                    {3, 1, 2, 4},
                    {7, 4, 1, 6},
                    {2, 3, 4, 1}
                },
                new String[]{"BC", "Alberta", "Ontario", "Saskatchewan"},
                new String[]{"June", "July", "August", "Sept"}
        );
        Log.p("Chart created");
        ChartView v = new ChartView(chart);
        v.initLater(new Runnable() {

            public void run() {
                l.setText("Chart loaded!");

            }
        });

        Log.p("View created");
        Command back = new Command("Back") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showMainMenu(true);
            }

        };
        f.setBackCommand(back);

        f.addComponent(BorderLayout.CENTER, v);
        f.addComponent(BorderLayout.NORTH, l);
        Log.p("About to show form");
        f.show();
        Log.p("Form shown");

    }
    
    private void showFlotExample(){
        Form f = new Form();
        f.setLayout(new BorderLayout());
        final WebBrowser web = new WebBrowser();
        web.setURL("http://people.iola.dk/olau/flot/examples/");
        f.addComponent(BorderLayout.CENTER, web);
        f.setBackCommand(new Command("Main Menu") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showMainMenu(true);
            }

        });
        
        
        f.show();
        
                
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }

}
