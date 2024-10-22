import os
import matplotlib.pyplot as plt
import sys
import ast

from sympy import Float, Integer

def crear_grafico_IMDA(categorias, valores, width, height, name, title):
    ####GRAFICO DE INDICE DE MADUREZ DIGITAL POR AMBITOS



    # Determinar colores para cada valor
    colores = []
    for valor in valores:
        if 0 <= valor <= 25:
            colores.append((129/255, 203/255, 238/255))  # Azul
        elif 25 < valor <= 50:
            colores.append((146/255, 209/255, 79/255))  # Verde
        elif 50 < valor <= 75:
            colores.append((255/255, 193/255, 0/255))  # Amarillo
        elif 75 < valor <= 100:
            colores.append((254/255, 0/255, 0/255))  # Rojo
        else:
            colores.append((0.5, 0.5, 0.5))  # Gris para valores fuera de rango


    color_borde = (173/255, 216/255, 230/255)  # Azul clarito en formato RGB normalizado

    # Crear el gráfico de barras horizontales
    plt.figure(figsize=(width, height))  # Ajustar el tamaño de la figura
    plt.barh(categorias, valores, color=colores, zorder = 3)

    plt.xticks([0, 25, 50, 75, 100])
    plt.tick_params(axis='x', width = 1)

    # Cambiar el color de las líneas de la grilla del eje x
    plt.grid(axis='x', color=color_borde, linestyle='--', linewidth=0.7, zorder = 0)

    # Cambiar el color del borde del área que engloba la gráfica
    plt.gca().spines['top'].set_edgecolor(color_borde)
    plt.gca().spines['right'].set_edgecolor(color_borde)
    plt.gca().spines['left'].set_edgecolor(color_borde)
    plt.gca().spines['bottom'].set_edgecolor(color_borde)

    # Opcional: cambiar el grosor del borde
    plt.gca().spines['top'].set_linewidth(2)
    plt.gca().spines['right'].set_linewidth(2)
    plt.gca().spines['left'].set_linewidth(2)
    plt.gca().spines['bottom'].set_linewidth(2)

    # Añadir título y etiquetas
    plt.title(title)

    # Añadir los valores dentro de las barras

    for index, value in enumerate(valores):
        plt.text(value + 0.5, index, str(value), va='center', fontsize=10, zorder = 4)

    # Ajustar el diseño para evitar que se corten los textos
    plt.tight_layout()

    # Mostrar la gráfica
    #plt.show()
    current_dir = os.getcwd()
    file_path = os.path.join(current_dir, 'src', 'util', 'chartsPython', 'graficaBarra' + name + '.png')
    plt.savefig(file_path, bbox_inches='tight')



def crear_circle_progress_bar(categoria, porcentaje, width, height, name):
    # Asegúrate de que el porcentaje esté entre 0 y 100


    if not (0 <= porcentaje <= 100):
        raise ValueError("El porcentaje debe estar entre 0 y 100.")

    # Determinar el color basado en el porcentaje
    if 0 <= porcentaje <= 25:
        color = '#81cbee' # blue
    elif 25 < porcentaje <= 50:
        color = '#92d14f' # green
    elif 50 < porcentaje <= 75:
        color = '#ffc100' # yellow
    elif 75 < porcentaje <= 100:
        color = '#fe0000' # red

    # Datos para el gráfico
    valores = [porcentaje, 100 - porcentaje]
    colores = [color, '#E0E0E0']  # Color para la categoría y color para el resto

    # Crear el gráfico de anillo
    plt.figure(figsize=(width, height))
    plt.pie(valores, colors=colores, startangle=90, counterclock=False, wedgeprops=dict(edgecolor='w'))

    # Añadir un círculo blanco en el centro para crear el efecto de anillo
    centro_circle = plt.Circle((0, 0), 0.70, color='white')
    fig = plt.gcf()
    fig.gca().add_artist(centro_circle)

    # Añadir texto con el porcentaje
    plt.text(0, 0, f'{porcentaje}%', ha='center', va='center', fontsize=20, fontweight='bold')

    # Configurar el título
    plt.title(categoria)

    # Mostrar el gráfico
    plt.axis('equal')  # Para que el gráfico se vea como un círculo
    #plt.show()
    current_dir = os.getcwd()
    file_path = os.path.join(current_dir, 'src', 'util', 'chartsPython', 'graficaCircular' + name + '.png')
    plt.savefig(file_path, bbox_inches='tight')


def crear_tabla(data, column_labels, title='Tabla'):
    """
    Crea una tabla utilizando Matplotlib.

    :param data: Lista de listas que contiene los datos de la tabla.
    :param column_labels: Lista con los nombres de las columnas.
    :param title: Título de la tabla.
    """
    # Crear una figura y un eje
    fig, ax = plt.subplots(figsize=(14, len(data) * 0.5))  # Ajustar el tamaño de la figura
    ax.axis('tight')
    ax.axis('off')

    # Crear la tabla
    tabla = ax.table(cellText=data, colLabels=column_labels, cellLoc='center', loc='center')

    # Personalizar encabezados
    for (i, j), cell in tabla.get_celld().items():
        if i == 0:  # Encabezados de columna
            cell.set_facecolor('#add8e6')  # Color azul claro
            cell.set_edgecolor('none')  # Sin líneas horizontales
            cell.set_text_props(fontsize=10, weight='bold', ha='center', va='center') # Alinear texto al centro
            cell.set_height(0.45)  # Ajustar altura de las celdas del encabezado
            # cell.set_width(1)  # Ancho automático basado en el contenido
        else:
            cell.set_edgecolor('none')  # Sin líneas horizontales
            # cell.set_text_props(ha='center', va='center')  # Alinear texto al centro


    # Establecer el título
    # plt.title(title, fontsize=16)

    # Personalizar la tabla (opcional)
    tabla.auto_set_font_size(False)
    tabla.set_fontsize(12)
    tabla.scale(1.2, 1.2)  # Escalar la tabla

    # Mostrar la tabla
    # plt.show()

    # Guardar la figura como imagen
    current_dir = os.getcwd()
    file_path = os.path.join(current_dir, 'src', 'util', 'chartsPython', 'tabla.png')
    # file_path = os.path.join(current_dir, 'src', 'python', 'tabla.png')
    plt.savefig(file_path, bbox_inches='tight', dpi=96)  # Ajustar el nombre y calidad de la imagen
    plt.close(fig)



if __name__ == "__main__":

    if(ast.literal_eval(sys.argv[1]) == 1 or ast.literal_eval(sys.argv[1]) == 2):
        categorias = ast.literal_eval(sys.argv[2])  # Convierte el string a lista
        valores = ast.literal_eval(sys.argv[3])      # Convierte el string a lista
        width = ast.literal_eval(sys.argv[4])
        height = ast.literal_eval(sys.argv[5])
        name = ast.literal_eval(sys.argv[6])

    if(ast.literal_eval(sys.argv[1]) == 1):
        crear_circle_progress_bar(categorias, valores, width, height, name)
    elif(ast.literal_eval(sys.argv[1]) == 2):
        title = ast.literal_eval(sys.argv[7])
        crear_grafico_IMDA(categorias, valores, width, height, name, title)
    elif(ast.literal_eval(sys.argv[1]) == 3):
        data = ast.literal_eval(sys.argv[2])
        column_labels = ast.literal_eval(sys.argv[3])
        width = ast.literal_eval(sys.argv[4])
        height = ast.literal_eval(sys.argv[5])
        crear_tabla(data, column_labels)
