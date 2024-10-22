package Interface.newInterface.python;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonExecutor {

    private String categorias;
    private String valores;

    public PythonExecutor(){
    }


    /**Este método es para crear un circle progress bar
     * @param categorias ⇒ El nombre de la categoría. (Solo puede ser una categoría)
     * @param valores ⇒ El porcentaje de la categoria con respecto a 100%. Debe ser un valor float pasado como String
     * @param width (ancho) ⇒ El ancho de la gráfica. Si el valor es "", width = 6, por defecto
     * @param height (alto) ⇒ El largo de la gráfica. Si el valor es "", heigth = 4, por defecto*/

    public static void pieChart(String categorias, String valores, String width, String height, String name){
        try {
            // Define las categorías y valores

            if(width.isEmpty())
                width = "3";

            if(height.isEmpty())
                height = "3";

            String a = System.getProperty("user.dir");
            ProcessBuilder pb = new ProcessBuilder("python", a+"/src/Interface/newInterface/python/PythonCharts.py", "1", categorias, valores, width, height, name);
            Process p = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader readers = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("lines"+line);
            }
            while ((line = readers.readLine()) != null) {
                System.out.println("Error lines"+line);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**Este método es para crear la gráfica de barras horizontales, tanto para ámbitos, como perspectivas
     * @param categorias ⇒ El nombre de la/las categorías
     * @param valores ⇒ El porcentaje de la/las categorías con respecto a 100%. Deben pasarse en forma de cadena:
     * <blockquote><pre>
     *        valores = [14.21, 23.43, 34,46]
     * </pre></blockquote><p>
     * <blockquote><pre>
     *        imdChart(categorias, valores)
     * </pre></blockquote><p>
     * @param width (ancho) ⇒ El ancho de la gráfica. Si el valor es "", width = 6, por defecto
     * @param height (alto) ⇒ El largo de la gráfica. Si el valor es "", heigth = 4, por defecto
     *     */
    public static void imdChart(String categorias, String valores, String width, String height, String name, String title){
        try {
            // Define las categorías y valores
            if(width.isEmpty())
                width = "6";

            if(height.isEmpty())
                height = "4";

            String a = System.getProperty("user.dir");
            ProcessBuilder pb = new ProcessBuilder("python", a+"/src/Interface/newInterface/python/PythonCharts.py", "2", categorias, valores, width, height, name, title);
            Process p = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader readers = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("lines"+line);
            }
            while ((line = readers.readLine()) != null) {
                System.out.println("Error lines"+line);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable(String categorias, String valores){
        try {

            String a = System.getProperty("user.dir");
            ProcessBuilder pb = new ProcessBuilder("python", a+"/src/Interface/newInterface/python/PythonCharts.py", "3", categorias, valores, "7", "4");
            Process p = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader readers = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("lines"+line);
            }
            while ((line = readers.readLine()) != null) {
                System.out.println("Error lines"+line);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
