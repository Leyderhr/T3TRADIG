package logic.useful;

import logic.DAO.DAOAmbito;
import logic.DAO.DAODimension;
import logic.DAO.DAOPerspectiva;
import logic.DAO.DAOPregunta;
import logic.Entitys.Ambito;
import logic.Entitys.Dimension;
import logic.Entitys.Perspectiva;
import logic.Entitys.Pregunta;

import java.util.ArrayList;


public class Controlador {

    public static void delete(Model model){
        updateCantPoints(model, 2);
        recursiveDelete(model.getId(), model.getLevel());
    }

    private static void recursiveDelete(int id, int level){
        switch (level){
            case 1:
                DAOAmbito daoAmbito = new DAOAmbito();
                daoAmbito.deleteAmbito(id);
                DAOPerspectiva dao_Perspectiva = new DAOPerspectiva();

                for (Perspectiva perspectiva : dao_Perspectiva.consultPerspectivas(id)) {
                    recursiveDelete(perspectiva.getId_perspectiva(), level + 1);
                }
                break;

            case 2:
                DAOPerspectiva daoPerspectiva = new DAOPerspectiva();
                daoPerspectiva.deletePerspectiva(id);
                DAODimension dao_Dimension = new DAODimension();

                for (Dimension dimension : dao_Dimension.consultDimensiones(id)) {
                    recursiveDelete(dimension.getId_dimension(), level + 1);
                }
                break;

            case 3:
                DAODimension daoDimension = new DAODimension();
                daoDimension.deleteDimension(id);
                DAOPregunta dao_Pregunta = new DAOPregunta();
                dao_Pregunta.deletePreguntaForDimension(id);
                break;

            case 4:
                DAOPregunta daoPregunta = new DAOPregunta();
                daoPregunta.deletePregunta(id);
                break;
            default: break;
        }
    }

    private static void updateCantPoints(Model model, int choise){
        switch (model.getLevel()){
            case 2:
                DAOPerspectiva daoPerspectiva = new DAOPerspectiva();
                recursiveCantUpdatePoints(model.getLevel() - 1, model.getSupId(), daoPerspectiva.findPerspectiva(model.getId()).getCant_preguntas(), 2);
                break;

            case 3:
                DAODimension daoDimension = new DAODimension();
                recursiveCantUpdatePoints(model.getLevel() - 1, model.getSupId(), daoDimension.findDimension(model.getId()).getCant_preguntas(), 2);
                break;

            case 4:
                recursiveCantUpdatePoints(model.getLevel() - 1, model.getSupId(), 1, choise);
                break;
            default: break;
        }
    }

    private static void recursiveCantUpdatePoints(int level, int id, int cant, int choise){
        switch (level){
            case 1:
                DAOAmbito daoAmbito = new DAOAmbito();
                Ambito ambito = daoAmbito.findAmbito(id);

                if(choise == 1)
                    ambito.setCant_preguntas(ambito.getCant_preguntas() + cant);
                else
                    ambito.setCant_preguntas(ambito.getCant_preguntas() - cant);

                daoAmbito.updateAmbito(ambito, 1);
                break;
            case 2:
                DAOPerspectiva daoPerspectiva = new DAOPerspectiva();
                Perspectiva perspectiva = daoPerspectiva.findPerspectiva(id);

                if(choise == 1)
                    perspectiva.setCant_preguntas(perspectiva.getCant_preguntas() + cant);
                else
                    perspectiva.setCant_preguntas(perspectiva.getCant_preguntas() - cant);

                daoPerspectiva.updatePerspectiva(perspectiva, 1);
                recursiveCantUpdatePoints(level - 1, perspectiva.getId_ambito(), cant, choise);
                break;
            case 3:
                DAODimension daoDimension = new DAODimension();
                Dimension dimension = daoDimension.findDimension(id);

                if(choise == 1)
                    dimension.setCant_preguntas(dimension.getCant_preguntas() + cant);
                else
                    dimension.setCant_preguntas(dimension.getCant_preguntas() - cant);

                daoDimension.updateDimension(dimension, 1);
                recursiveCantUpdatePoints(level - 1, dimension.getId_perspectiva(), cant, choise);
                break;
            default: break;
        }
    }

    public static void calculatePoints(){
        DAOPregunta daoPregunta = new DAOPregunta();
        DAODimension daoDimension = new DAODimension();
        DAOPerspectiva daoPerspectiva = new DAOPerspectiva();
        DAOAmbito daoAmbito = new DAOAmbito();

        ArrayList<Dimension> dimensions = daoDimension.consultDimensiones();
        daoPregunta.calculatePoints(dimensions);

        daoDimension.updateDimension(dimensions);
        ArrayList<Perspectiva> perspectivas = daoPerspectiva.consultPerspectivas();
        daoDimension.calculatePoints(perspectivas);

        daoPerspectiva.updatePerspectiva(perspectivas);
        ArrayList<Ambito> ambitos = daoAmbito.consultAmbitos();
        daoPerspectiva.calculatePoints(ambitos);

        daoAmbito.updateAmbito(ambitos);
    }

    public static void savePreguntas(ArrayList<Pregunta> questions){
        DAOPregunta daoPregunta = new DAOPregunta();
        daoPregunta.updatePregunta(questions);
    }

    private static void addRecicleBin(){

    }

    public static void insert(Model model){
        switch (model.getLevel()){
            case 1:
                Ambito ambito = new Ambito(model.getId(), 0, 0, model.getLine());
                DAOAmbito daoAmbito = new DAOAmbito();
                daoAmbito.insertAmbito(ambito);
                break;
            case 2:
                Perspectiva perspectiva = new Perspectiva(model.getId(), 0, 0, model.getSupId(), model.getLine());
                DAOPerspectiva daoPerspectiva = new DAOPerspectiva();
                daoPerspectiva.insertPerspectiva(perspectiva);
                break;
            case 3:
                Dimension dimension = new Dimension(model.getId(), 0, 0, model.getSupId(), model.getLine());
                DAODimension daoDimension = new DAODimension();
                daoDimension.insertDimension(dimension);
                break;
            case 4:
                Pregunta pregunta = new Pregunta(model.getId(), 0, model.getSupId(), model.getLine());
                DAOPregunta daoPregunta = new DAOPregunta();
                daoPregunta.insertPregunta(pregunta);
                updateCantPoints(model, 1);
                break;
            default: break;
        }
    }

    public static void recover(){

    }

    public static ArrayList<Ambito> getAmbitos(){
        DAOAmbito dao = new DAOAmbito();
        return dao.consultAmbitos();
    }

    public static ArrayList<Perspectiva> getPerspectiva(int id){
        DAOPerspectiva dao = new DAOPerspectiva();

        if(id > 0)
            return dao.consultPerspectivas(id);
        else
            return dao.consultPerspectivas();
    }

    public static ArrayList<Dimension> getDimension(int id){
        DAODimension dao = new DAODimension();

        if(id > 0)
            return dao.consultDimensiones(id);
        else
            return dao.consultDimensiones();
    }

    public static ArrayList<Pregunta> getPregunta(int id){
        DAOPregunta dao = new DAOPregunta();

        if(id > 0)
            return dao.consultPregunta(id);
        else
            return dao.consultPregunta();
    }

    public static Ambito findAmbito(int id){
        DAOAmbito dao = new DAOAmbito();
        return dao.findAmbito(id);
    }

    public static Perspectiva findPerspectiva(int id){
        DAOPerspectiva dao = new DAOPerspectiva();
        return dao.findPerspectiva(id);
    }

    public static Dimension findDimension(int id){
        DAODimension dao = new DAODimension();
        return dao.findDimension(id);
    }

    public static Pregunta findPregunta(int id){
        DAOPregunta dao = new DAOPregunta();
        return dao.findPregunta(id);
    }
}
