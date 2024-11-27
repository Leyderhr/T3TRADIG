package Interface.newInterface.Chart;


import logic.Entitys.Dimension;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


// Panel class for displaying the pie chart
class DonutChartPanel extends JPanel {

    // Initial data values representing percentages
    private final float[] data;

    private final Color[] sliceColors = {
            new Color(1, 176, 239, 255),
            new Color(146, 209, 79, 255),
            new Color(255, 193, 0, 255),
            new Color(254, 0, 0, 255)};
    // Updated colors

    private final String category[] = {
            "Básico", "Inicial", "Estratégico", "Innovador - Disruptivo"
    };

    private int[] cant;

    public DonutChartPanel(ArrayList<Dimension> dimensions){
        int blue = 0, green = 0, yellow = 0, red = 0;

        for (Dimension dimension : dimensions) {
            if (dimension.calculate_MDr_IMD()[1] >= 0 && dimension.calculate_MDr_IMD()[1] <= 25)
                blue++;
            if (dimension.calculate_MDr_IMD()[1] > 25 && dimension.calculate_MDr_IMD()[1] <= 50)
                green++;
            if (dimension.calculate_MDr_IMD()[1] > 50 && dimension.calculate_MDr_IMD()[1] <= 75)
                yellow++;
            if (dimension.calculate_MDr_IMD()[1] > 75 && dimension.calculate_MDr_IMD()[1] <= 100)
                red++;
        }

        data = new float[4];
        data[0] = ((float) blue * 100f) / (float)dimensions.size();
        data[1] = ((float) green * 100f) / (float)dimensions.size();
        data[2] = ((float) yellow * 100f) / (float)dimensions.size();
        data[3] = ((float) red * 100f) / (float)dimensions.size();

        cant = new int[4];
        cant[0] = blue;
        cant[1] = green;
        cant[2] = yellow;
        cant[3] = red;

    }


    // Override the paintComponent method to customize the drawing of the panel
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        // Call the method to draw the pie chart
        drawDonutChart(g);

    }

    // Method to draw the pie chart
    private void drawDonutChart(Graphics g) {
        // Create a Graphics2D object
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Get the width of the panel
        int width = getWidth();
        // Get the height of the panel
        int height = getHeight() - 100;

        // Determine the diameter of the pie chart
        int outerDiameter = Math.min(width, height) - 20;
        int innerDiameter = outerDiameter / 2;
        // Calculate the x-coordinate for the pie chart
        int x = (width - outerDiameter) / 2;
        // Calculate the y-coordinate for the pie chart
        int y = (height - outerDiameter) / 2;
        // Initialize the starting angle for the first slice of the pie chart
        int startAngle = 0;

        for (int i = 0; i < data.length; i++) {
            // Calculate the arc angle for the current slice
            int arcAngle = (int) ((double) data[i] / 100 * 360);
            // Set the color for the current slice
            g2d.setColor(sliceColors[i]);
            // Fill the arc representing the slice
            g2d.fillArc(x, y, outerDiameter, outerDiameter, startAngle, arcAngle);
            g2d.setColor(getBackground());
            g2d.fillArc(x + (outerDiameter - innerDiameter) / 2, y + (outerDiameter - innerDiameter) / 2, innerDiameter, innerDiameter, 0, 360);
            // Update the starting angle for the next slice
            startAngle += arcAngle;
        }

        // Draw labels or legends for each slice
        // Set the x-coordinate for the legend
        int legendX = 10;
        // Set the initial y-coordinate for the legend
        int legendY = height - 50;

        for (int i = 0; i < data.length; i++) {
            // Set the color for the legend box
            g2d.setColor(sliceColors[i]);
            // Fill the legend box with color
            g2d.fillRect(legendX, legendY, 20, 20);
            // Set the text color for the legend
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString(category[i] + ": "+ cant[i] +"-" +"("+Math.round(data[i]) + "%)", legendX + 30, legendY + 15);
            // Update the y-coordinate for the next legend entry
            legendY += 30;
        }

    }
}