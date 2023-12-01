package com.example.geoexplora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

public class ContenidosFragment extends Fragment {
    ListView listView;
    List<Contenidos> temasGeografia;
    List<Imagenes> imagenes;
    OperacionesContenidos contenidos;
    OperacionesImagenes imagen;
    ContenidosAdapter contenidosAdapter;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_INSTALLATION_DONE = "installationDone";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contenidoslistview, container, false);

        // Esta es una manera de obtener una instancia de SQLiteDatabase para realizar operaciones.

        contenidos=new OperacionesContenidos(getContext());
        imagen=new OperacionesImagenes(getContext());

        contenidos.abrir();
        imagen.abrir();



        // Verificar si la instalación ya se ha realizado
        if (!isInstallationDone()) {

            // Realizar inserciones
            insercionesImagenes();
            insercionesContendos();
            // Marcar la instalación como completada
            setInstallationDone();


        }

            listView=view.findViewById(R.id.listview);
            temasGeografia=contenidos.obtenerTodosLosContenidos();
            imagenes=imagen.obtenerTodasLasImagenes();
            contenidosAdapter= new ContenidosAdapter(this.getActivity(),temasGeografia);
            listView.setAdapter(contenidosAdapter);
            listView.setOnItemClickListener(evento);



        contenidos.cerrar();
        imagen.cerrar();
        return view;

    }

    private AdapterView.OnItemClickListener evento=(adapterview,view,i,l)->{


        Contenidos contenidos=(Contenidos) adapterview.getItemAtPosition(i);
        if(temasGeografia.get(i).getBandera()==1){
            Intent intent=new Intent(getActivity(),ContenidosActivity.class);
            intent.putExtra("tema", temasGeografia.get(i));
            intent.putExtra("imagenes",imagenes.get(i));
            startActivity(intent);
        }
        else{
            // Crear un Toast personalizado
            Toast customToast = Toast.makeText(this.getActivity(), "", Toast.LENGTH_SHORT);

        // Obtener el View del Toast
            View toastView = customToast.getView();

        // Inflar el diseño personalizado del Toast
            LayoutInflater inflater = getLayoutInflater();
            View customToastView = inflater.inflate(R.layout.toast_personalizado, null);

        // Obtener la TextView (contenido) y la ImageView (imagen)
            TextView textView = customToastView.findViewById(R.id.textToast);
            ImageView imageView = customToastView.findViewById(R.id.imagenToast);

        // Personalizar el texto y la imagen aquí si es necesario
            textView.setText("Aún te falta concluir el tema: " + temasGeografia.get(i-1).getTema());
            imageView.setImageResource(temasGeografia.get(i-1).getImagen());
            textView.setTextColor(Color.WHITE);

         // Aplicar el fondo personalizado al contenido del Toast
            customToastView.setBackgroundResource(R.drawable.gradiente_card); // El nombre del archivo XML

        // Establecer la vista personalizada en el Toast
            customToast.setView(customToastView);
            customToast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 50);

        // Mostrar el Toast personalizado
            customToast.show();
        }

    };

    void insercionesImagenes(){

        imagen.insertarImagenes(R.drawable.historia1,R.drawable.historia2,R.drawable.histori3,R.drawable.historia4,R.drawable.historia5);
        imagen.insertarImagenes(R.drawable.tierra1,R.drawable.tierra2,R.drawable.tierra3,R.drawable.tierra4,R.drawable.tierra5);
        imagen.insertarImagenes(R.drawable.estructura1,R.drawable.estructura2,R.drawable.estrcutura3,R.drawable.estructura4,R.drawable.estructura5);
        imagen.insertarImagenes(R.drawable.continente1,R.drawable.continenete2,R.drawable.continente3,R.drawable.continente5,R.drawable.continente4);
        imagen.insertarImagenes(R.drawable.mapa2,R.drawable.mapa1,R.drawable.mapa3,R.drawable.mapa4,R.drawable.mapa5);
        imagen.insertarImagenes(R.drawable.oceanos1,R.drawable.oceanos2,R.drawable.oceano3,R.drawable.oceano5,R.drawable.oceano4);
        imagen.insertarImagenes(R.drawable.montana1,R.drawable.montana2,R.drawable.montana3,R.drawable.montana4,R.drawable.montana5);
        imagen.insertarImagenes(R.drawable.mexico1,R.drawable.mexico2,R.drawable.mexico3,R.drawable.mexico4,R.drawable.mexico5);
        imagen.insertarImagenes(R.drawable.poblacion1,R.drawable.poblacion2,R.drawable.poblacion3,R.drawable.poblacion4,R.drawable.poblacion5);
        imagen.insertarImagenes(R.drawable.recursos1,R.drawable.recursos2,R.drawable.recursos3,R.drawable.recursos4,R.drawable.recursos5);


    }
    void insercionesContendos(){


        contenidos.insertarContenido("Introduccion a la geografia",
                R.drawable.introduccion,
                "La geografía es una disciplina que aborda el estudio de la superficie terrestre en sus diversas dimensiones. Originada en el griego, la palabra \"geografía\" significa \"escribir\" o \"describir\" la Tierra, encapsulando su propósito de observar, analizar y describir la interacción de los seres humanos con el entorno.\n" +
                        " Sus objetivos incluyen la comprensión de la estructura terrestre, el análisis de las interacciones humanas con el entorno y la exploración de las diferencias regionales en culturas, economías y sociedades.\n" +
                        "La geografía se divide en varias ramas, entre las cuales se encuentran la geografía física, centrada en aspectos como la topografía y el clima, y la geografía humana, que se ocupa de las actividades y comportamientos de las poblaciones humanas. Otras subdivisiones abordan diferencias regionales específicas, como la geografía regional.\n" +
                        "La disciplina también se extiende a la geografía económica, que analiza las actividades económicas humanas en relación con el entorno geográfico, y la geografía política, enfocada en divisiones políticas y conflictos geopolíticos. La geografía cultural, por otro lado, explora las diversas culturas y grupos étnicos en diferentes partes del mundo, junto con la influencia de la geografía en la cultura.\n" +
                        "La importancia de la geografía radica en su papel fundamental para comprender el mundo y tomar decisiones informadas en áreas como la planificación urbana, la gestión de recursos naturales, la política y la economía. Facilita la comprensión de la interacción entre la Tierra y la humanidad, siendo esencial para abordar desafíos globales como el cambio climático, la migración y la sostenibilidad.\n",
                1);
        contenidos.insertarContenido("El planeta tierra",
                R.drawable.tierra,
                "La Tierra, como tercer planeta del sistema solar, es único al albergar vida. Su estructura geológica incluye una corteza sólida, un manto semilíquido y núcleos interno y externo. La atmósfera, compuesta principalmente por nitrógeno y oxígeno, protege contra la radiación solar y regula el clima.\n" +
                        "Con aproximadamente el 70% de su superficie cubierta por océanos y el 30% restante por continentes, la Tierra muestra una diversidad geográfica impresionante.\n" +
                        " Desde climas desérticos hasta selvas tropicales y regiones polares, exhibe una variedad asombrosa de condiciones climáticas. La biósfera, que abarca desde selvas tropicales hasta desiertos áridos, es el hogar de una amplia variedad de especies.\n" +
                        "Como único hogar conocido de vida en el universo, la Tierra sustenta millones de especies, incluidos los seres humanos, proporcionando recursos esenciales para su supervivencia. \n" +
                        "Más allá de ser un refugio vital, la Tierra es un objeto constante de estudio y exploración. Desde la evolución del planeta hasta el cambio climático, la sismología y la geología, la Tierra sigue siendo un fascinante campo de investigación que ofrece oportunidades continuas para expandir nuestro conocimiento científico.\n"
                ,0);
        contenidos.insertarContenido("La estructura de la tierra",
                R.drawable.atmosfera,
                "La atmósfera que rodea la Tierra es crucial para mantener la vida en nuestro planeta, y su estructura se compone de diversas capas con características distintas. \n" +
                        "La troposfera, la capa más cercana a la superficie, se extiende hasta 15 kilómetros sobre los polos y 18 kilómetros sobre el ecuador, donde la temperatura disminuye con la altitud. La mayoría de los fenómenos climáticos ocurren aquí, incluyendo la formación de nubes y la lluvia.\n" +
                        "Por encima de la troposfera está la estratosfera, que se extiende de 18 a 50 kilómetros de altitud. Aquí, la temperatura aumenta debido a la capa de ozono, crucial para proteger la vida en la Tierra de la radiación ultravioleta. La mesosfera, entre 50 y 85 kilómetros, es donde se forman nubes noctilucentes y donde los meteoroides se queman al entrar en la atmósfera.\n" +
                        "La termosfera, a altitudes de 85 kilómetros o más, absorbe la radiación ultravioleta y los rayos X del sol, lo que provoca un aumento significativo de temperatura a pesar de que la sensación de calor es mínima debido a la baja densidad del aire. \n" +
                        "Finalmente, la exosfera, la capa más alta, se extiende desde la termosfera hasta el espacio exterior, con una densidad de aire extremadamente baja, donde los satélites orbitan alrededor de la Tierra. Cada capa desempeña un papel fundamental en la regulación de la temperatura, la protección contra la radiación dañina y en la dinámica climática y meteorológica de la Tierra, contribuyendo así a su capacidad para sustentar la vida.\n",
                0);
        contenidos.insertarContenido("Los continentes",
                R.drawable.continentes,
                "Los continentes, como las principales masas de tierra de la Tierra, ofrecen una diversidad geográfica, cultural y natural única. Asia, el más extenso, abarca aproximadamente el 30% de la superficie terrestre, destacando por sus altas montañas, vastos desiertos y extensas costas.\n" +
                        " África, el segundo continente más grande con alrededor del 20% de la superficie, es conocido por su rica biodiversidad, el desierto del Sahara y una variedad de culturas.\n" +
                        "América del Norte, el tercero en tamaño con aproximadamente el 17%, incluye países como Estados Unidos y Canadá, ofreciendo paisajes que van desde montañas hasta vastas llanuras. Sudamérica, el cuarto en tamaño con cerca del 12%, destaca por la selva amazónica, los Andes y su diversidad cultural. La Antártida, el quinto continente más grande con alrededor del 9%, es el más frío y está dedicado a la investigación científica.\n" +
                        "Europa, el sexto continente en tamaño que abarca aproximadamente el 7%, destaca por su rica historia y variada cultura. \n" +
                        "Australia, el séptimo en tamaño con cerca del 5%, es una isla-continente conocida por su fauna única y diversos paisajes, incluyendo el Outback australiano. Cada continente, con su propia historia y biodiversidad, desempeña un papel crucial en la geografía política y económica global. La diversidad de los continentes refleja la capacidad única de la Tierra para albergar una amplia variedad de formas de vida y civilizaciones.\n",
                0);
        contenidos.insertarContenido("Mapa geografico",
                R.drawable.mapageo,
                "Un mapa geográfico es una representación visual de una región o área que muestra la disposición espacial de elementos como continentes, países, ciudades, ríos y montañas. \n" +
                        "Estas herramientas son esenciales para la navegación, la planificación, la educación y la comprensión de la Tierra. Los elementos comunes en un mapa incluyen el título que describe el área y propósito, la leyenda que explica símbolos y colores, la escala que relaciona distancias en el mapa con la realidad, la orientación que indica la dirección norte, y las coordenadas geográficas para ubicar puntos precisos.\n" +
                        "Existen diversos tipos de mapas, como políticos, que muestran fronteras y ciudades; físicos, que representan características naturales; topográficos, que detallan elevaciones y valles; climáticos, que reflejan patrones climáticos; de carreteras, útiles para la navegación; y temáticos, centrados en temas específicos como densidad de población o recursos naturales. Los mapas son herramientas valiosas para la navegación, la planificación urbana, la educación y la investigación científica.\n" +
                        "Su uso abarca desde la orientación y la planificación urbana hasta la educación y la investigación científica. Desde mapas antiguos hasta aplicaciones móviles modernas, las representaciones cartográficas han evolucionado, pero siguen siendo esenciales en la vida cotidiana. \n" +
                        "Los mapas geográficos desempeñan un papel crucial en la comprensión de la Tierra y en la toma de decisiones en un mundo cada vez más interconectado.\n" ,             0);
        contenidos.insertarContenido("Los oceanos y mares",
                R.drawable.oceanos,
                "Los océanos y mares, extensiones acuáticas que cubren la mayor parte de la Tierra, se dividen en océanos principales y cuerpos de agua más pequeños rodeados parcial o completamente de tierra.\n" +
                        " El Océano Pacífico, el más grande y profundo, se extiende desde el Ártico hasta la Antártida, albergando islas como Hawái y Filipinas. El Océano Atlántico, el segundo en tamaño, se encuentra entre América, Europa y África, incluyendo el Mar Caribe y el Mediterráneo.\n" +
                        "El Océano Índico, el tercero en tamaño, abarca regiones entre África, Asia y Australia, conteniendo el Mar Rojo y el Golfo Pérsico. El Océano Antártico, que rodea la Antártida, es el más frío y hogar de vida marina única. Entre los mares significativos, el Mar Mediterráneo destaca por su historia y costa diversa, mientras que el Mar Caribe, rodeado por América Central y el Caribe, es conocido por sus playas y biodiversidad.\n" +
                        "El Mar del Norte, clave para la navegación y pesca, se encuentra en el norte de Europa. El Mar Rojo, entre el noreste de África y la Península Arábiga, es famoso por su biodiversidad. El Mar de China Meridional, rodeado por países del sudeste asiático, es vital para la navegación y fuente de tensiones geopolíticas. Otros mares notables incluyen el Mar de Noruega, Mar de Japón y Mar Negro, cada uno con su importancia económica, histórica y cultural.\n" +
                        "Estos océanos y mares no solo son esenciales para la vida y la biodiversidad, sino que también desempeñan un papel clave en la economía, la navegación y la cultura. Además de proporcionar recursos naturales, actúan como destinos turísticos populares y tienen un impacto significativo en la historia y la identidad de las regiones circundantes\n",                0);
        contenidos.insertarContenido("Montañas y volcanes",
                R.drawable.montana,
                "Montañas y volcanes, características geográficas notables en todo el mundo, comparten la singularidad de elevarse por encima del terreno circundante. \n" +
                        "Las montañas, elevaciones naturales, se forman por procesos geológicos como la colisión de placas tectónicas, actividad volcánica, plegamiento de capas de roca y erosión. Pueden clasificarse en montañas plegadas, como los Alpes; montañas volcánicas, ejemplificadas por el Monte Fuji; y montañas de bloque, como las Rocosas, cada una con impactos significativos en la geografía, ecología y cultura terrestres.\n" +
                        "En cuanto a los volcanes, aberturas en la corteza terrestre, se originan por la actividad geológica, con magma ascendiendo desde el manto terrestre. Variedades incluyen volcanes en cono, como el Vesuvio; en escudo, como el Mauna Loa; y compuestos, como el Monte St. Helens. \n" +
                        "Los volcanes son cruciales en la formación de tierras, el reciclaje de minerales y la liberación de gases a la atmósfera, aunque sus erupciones pueden ser peligrosas y tener impactos significativos.\n" +
                        "Ambos, montañas y volcanes, son fundamentales en la geografía terrestre, influyendo en la distribución del agua, el clima y la biodiversidad. Además, su importancia cultural, turística y su capacidad para inspirar fascinación a lo largo de la historia los convierte en elementos esenciales en la vida de las personas y en la configuración de la superficie terrestre.\n",                0);
        contenidos.insertarContenido("Geografia de Mexico",
                R.drawable.mexico,
                "México, ubicado en América del Norte, se distingue por su rica diversidad geográfica, desde playas tropicales hasta elevadas montañas. Limita con Estados Unidos al norte y Belice y Guatemala al sur, con costas en el océano Pacífico al oeste y el golfo de México y el mar Caribe al este.\n" +
                        "Las cadenas montañosas de la Sierra Madre Occidental y Oriental atraviesan el país, mientras que el Eje Volcánico Transversal alberga prominentes volcanes, incluyendo el Pico de Orizaba, la montaña más alta de América del Norte. En el norte, se encuentran los vastos desiertos de Sonora y Chihuahua, conocidos por su aridez. La Península de Yucatán, en el sureste, destaca por sus playas y sitios arqueológicos mayas, mientras que la Selva Lacandona en el sureste es una región biodiversa.\n" +
                        "Las costas a lo largo del golfo de México y el océano Pacífico, con ciudades como Veracruz y Acapulco, son reconocidas por sus playas. La Cuenca del Valle de México, hogar de la Ciudad de México, se encuentra en un valle rodeado de montañas y volcanes.\n" +
                        "México cuenta con importantes ríos como el Río Bravo y el Río Usumacinta, así como lagos notables como el Lago de Chapala. La variada geografía contribuye a una amplia gama de climas, desde tropical en las costas hasta árido en el norte y templado en las tierras altas.\n" +
                        "En términos de biodiversidad, México se destaca como uno de los países más ricos del mundo, albergando una amplia variedad de especies, algunas endémicas y en peligro de extinción. La diversidad geográfica de México ha dejado una huella significativa en su cultura, economía y paisaje natural, ofreciendo experiencias diversas tanto para residentes como para visitantes\n" +
                        "\n",                0);
        contenidos.insertarContenido("La población mundial",
                R.drawable.poblacion,
                "La población mundial, que supera los 7,8 mil millones de personas en 2021, exhibe un crecimiento variable en distintas regiones del planeta. La distribución geográfica muestra densidades poblacionales significativas en áreas como Asia oriental y meridional, Europa, América del Norte y del Sur, mientras que regiones como el Ártico y el desierto del Sahara tienen poblaciones escasas.\n" +
                        "La diversidad cultural es una característica fundamental de la humanidad, manifestándose a través de distintas historias, lenguas, religiones, tradiciones y expresiones artísticas. La interacción entre culturas ha generado la riqueza de la diversidad cultural en el mundo.\n" +
                        "Las religiones, como el cristianismo, islam, hinduismo y budismo, desempeñan un papel vital en la identidad y cultura de muchas sociedades. La multiplicidad lingüística se refleja en más de 7,000 idiomas, destacando lenguas como el inglés, español, chino mandarín y árabe con un gran número de hablantes.\n" +
                        "Las festividades y tradiciones, como el Año Nuevo chino, Diwali hindú, Ramadán islámico y la Navidad cristiana, enriquecen la vida cultural. El arte y la literatura reflejan la creatividad y la identidad cultural, mientras que la gastronomía única de cada cultura, ejemplificada en platos como la pizza italiana, el sushi japonés y el curry indio, contribuye a la diversidad culinaria.\n" +
                        "En la era de la globalización, la interconexión facilita la difusión de culturas a través de la migración, los medios de comunicación y la tecnología. Este intercambio constante entre distintas culturas enriquece nuestras vidas y amplía nuestra comprensión del mundo, destacando la importancia de la diversidad y la interconexión en la sociedad global.\n",
                0);
        contenidos.insertarContenido("Recursos naturales",
                R.drawable.recursosnaturales,
                "Los recursos naturales, esenciales para el bienestar humano y el desarrollo económico, se dividen en renovables y no renovables. Entre los renovables, el agua dulce es vital para la vida y diversas aplicaciones. La energía solar, obtenida del sol mediante paneles solares, y la energía eólica, generada por el viento a través de aerogeneradores, son fuentes limpias y renovables.\n" +
                        " Los bosques, además de proveer madera y productos forestales, son fundamentales para la captura de carbono y la biodiversidad. La pesca sostenible en océanos y cuerpos de agua garantiza la disponibilidad de alimentos y la conservación de ecosistemas acuáticos.\n" +
                        "Los recursos como el petróleo y el gas son como tesoros que usamos para obtener energía y hacer cosas especiales. Pero, ¡tenemos que cuidarlos porque no durarán para siempre! Los minerales, como el hierro y el oro, son como piedras mágicas que nos ayudan a hacer cosas increíbles. Algunos, como el carbón, nos dan energía, pero debemos usarlos con cuidado para no hacer daño al aire que respiramos. ¡Ah, y el uranio es como una varita mágica en las plantas de energía! Recuerda, ¡cuidar de estos tesoros es muy importante para nuestro planeta y para ti!\n" +
                        "Nuestra Tierra es como un gran tesoro que nos da cosas importantes. La tierra nos ayuda a cultivar alimentos, pero debemos cuidarla para que no se deteriore. El aire, que necesitamos para respirar, debe estar limpio. El agua, que es vital para muchas cosas que hacemos, también necesita ser cuidada. Los lugares bonitos y diferentes en la Tierra son como tesoros especiales que debemos proteger. ¡Cuidar de la Tierra es como cuidar de nuestro hogar! \uD83C\uDF0E✨\n" +
                        "La gestión sostenible de estos recursos es crucial para asegurar su disponibilidad para las generaciones futuras, ya que la sobreexplotación y la degradación pueden tener consecuencias ambientales y sociales graves. Conservar y utilizar estos recursos de manera responsable es esencial para una gestión ambiental efectiva.\n",                0);

    }
    private boolean isInstallationDone() {
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_INSTALLATION_DONE, false);
    }

    private void setInstallationDone() {
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_INSTALLATION_DONE, true);
        editor.apply();
    }
}


