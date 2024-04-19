import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import org.jdom2.JDOMException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import org.jdom2.input.SAXBuilder;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.DocType;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
* Clase que cambia un archivo XML en un objeto en java y viceversa
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class Parser {

    /**
    * Convierte un archivo XML en un ArrayList de Pokemon
    *
    * @return pokedex - ArrayList con todos los pokémon disponibles
    */
    public ArrayList<Pokemon> parsePokemon() throws JDOMException, IOException, IllegalStateException{

        SAXBuilder lectorSAX = new SAXBuilder();
        Document doc = lectorSAX.build(new FileInputStream("Pokedex.xml"));
        Element raiz = doc.getRootElement();
        List<Element> entradas = raiz.getChildren();
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
        for (Element entrada : entradas) {
            Pokemon pokemon = new Pokemon();
            pokemon.setNombre(entrada.getAttributeValue("nombre"));
            pokemon.setTipo(entrada.getAttributeValue("tipo"));
            pokemon.setVida(Integer.parseInt(entrada.getAttributeValue("vida")));
            pokemon.setAtaque(Integer.parseInt(entrada.getAttributeValue("ataque")));
            pokemon.setDefensa(Integer.parseInt(entrada.getAttributeValue("defensa")));
            pokemon.setVelocidad(Integer.parseInt(entrada.getAttributeValue("velocidad")));
            pokedex.add(pokemon);
        }
        return pokedex;
    }

    /**
    * Convierte un archivo XML en un ArrayList de entrenadores
    *
    * @return listaEntrenadores - ArrayList con todos los entrenadores registrados
    */
    public ArrayList<Entrenador> parseEntrenadores() throws JDOMException, IOException, IllegalStateException{

        SAXBuilder lectorSAX = new SAXBuilder();
        Document doc = lectorSAX.build(new FileInputStream("Entrenadores.xml"));
        Element raiz = doc.getRootElement();
        List<Element> entradas = raiz.getChildren();
        ArrayList<Entrenador> listaEntrenadores = new ArrayList<Entrenador>();
        for (Element entrada : entradas) {
            Entrenador entrenador = new Entrenador();
            entrenador.setNombre(entrada.getAttributeValue("nombre"));
            entrenador.setContrasena(entrada.getAttributeValue("contrasena"));
            entrenador.setMejorRecord(Integer.parseInt(entrada.getAttributeValue("mejorRecord")));
            listaEntrenadores.add(entrenador);
        }
        return listaEntrenadores;
    }

    /**
    * Actualiza el archivo XML con los entrenadores
    *
    * @param entrenadores - ArrayList de entrenadores con la que se actualizara el archivo
    */
    public void actualizarBDEntrenadores(ArrayList<Entrenador> entrenadores){
        try {
            Element bd = new Element("bd");
            bd.setAttribute(new Attribute("id", "Entrenadores.xml"));
            bd.setAttribute(new Attribute("elementos", Integer.toString(entrenadores.size())));

            Document doc = new Document(bd);
            doc.setDocType(new DocType("bd"));

            for (Entrenador e : entrenadores) {
                Element entrada = new Element("entrenador");
                entrada.setAttribute(new Attribute("nombre", e.getNombre()));
                entrada.setAttribute(new Attribute("contrasena", e.getContrasena()));
                entrada.setAttribute(new Attribute("mejorRecord", Integer.toString(e.getMejorRecord())));

                bd.addContent(entrada);
            }

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileOutputStream("Entrenadores.xml"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
