package com.example.projectmovil.data

// Importamos la clase del mismo paquete
import com.example.projectmovil.data.HistoricalRecipe

// Objeto Singleton que proporciona la lista de recetas estáticas (PROVEEDOR)
object HistoricalRecipeProvider {

    val historicalRecipes = listOf(
        HistoricalRecipe(
            name = "Sopa Egipcia de Lentejas (Shorbat Ads)",
            shortDescription = "Sopa básica consumida en el antiguo Egipto",
            type = "Sopa", // Tipo
            rating = "⭐ 4.6",
            calories = "280 kcal por porción",
            history = "La sopa de lentejas era un alimento común en el antiguo Egipto. Los trabajadores de las pirámides la consumían por ser barata, nutritiva y fácil de preparar. Está registrada en textos agrícolas del Imperio Medio.",
            ingredients = listOf(
                "1 taza de lentejas rojas",
                "1 cebolla picada",
                "2 dientes de ajo",
                "1 cucharadita de comino",
                "1 cucharada de aceite",
                "4 tazas de agua",
                "Sal al gusto"
            ),
            preparation = listOf(
                "Lavar las lentejas y escurrirlas.",
                "Calentar aceite y sofreír la cebolla y ajo.",
                "Agregar el comino y mezclar.",
                "Incorporar las lentejas y el agua.",
                "Hervir a fuego medio 25 minutos.",
                "Sazonar y triturar ligeramente para espesar."
            )
        ),

        HistoricalRecipe(
            name = "Gachas Romanas (Puls)",
            shortDescription = "Plato de cebada básico en la antigua Roma",
            type = "Plato Fuerte", // Tipo
            rating = "⭐ 4.0",
            calories = "250 kcal por porción",
            history = "La puls era el alimento cotidiano de los romanos pobres y soldados. Se preparaba con harina de cebada y agua, a veces enriquecida con queso o hierbas. Aparece en textos de Catón el Viejo.",
            ingredients = listOf(
                "1 taza de harina de cebada",
                "2 tazas de agua",
                "1 cucharadita de sal",
                "1 cucharada de aceite de oliva",
                "Queso rallado opcional"
            ),
            preparation = listOf(
                "Calentar agua con sal hasta que hierva.",
                "Añadir la harina de cebada lentamente.",
                "Cocinar revolviendo 15-20 minutos.",
                "Agregar aceite y mezclar.",
                "Servir con queso si se desea."
            )
        ),

        HistoricalRecipe(
            name = "Pan Naan Persa Antiguo",
            shortDescription = "Pan plano horneado en tandoor",
            type = "Pan", // Tipo
            rating = "⭐ 4.7",
            calories = "210 kcal por pieza",
            history = "El naan tiene origen en Persia alrededor del siglo XII. Fue mencionado en textos persas medievales como pan de élite servido en banquetes y palacios.",
            ingredients = listOf(
                "2 tazas de harina",
                "½ taza de agua tibia",
                "1 cucharadita de levadura",
                "2 cucharadas de yogurt",
                "1 cucharadita de sal"
            ),
            preparation = listOf(
                "Mezclar levadura con agua tibia.",
                "Agregar harina, yogurt y sal.",
                "Amasar 10 minutos y dejar reposar 1 hora.",
                "Formar discos y estirar.",
                "Cocer en sartén o tandoor 2-3 minutos por lado."
            )
        ),

        HistoricalRecipe(
            name = "Atole Maya de Maíz",
            shortDescription = "Bebida espesa prehispánica de maíz",
            type = "Bebida", // Tipo
            rating = "⭐ 4.5",
            calories = "180 kcal por taza",
            history = "Los mayas consumían atoles desde el Preclásico, usando maíz nixtamalizado molido y agua. Estaba relacionado con rituales agrícolas dedicados al dios Yum Kaax.",
            ingredients = listOf(
                "1 taza de masa de maíz",
                "3 tazas de agua",
                "1 rama de canela",
                "2 cucharadas de miel"
            ),
            preparation = listOf(
                "Disolver la masa en una taza de agua.",
                "Calentar el resto del agua con canela.",
                "Agregar la mezcla de masa.",
                "Cocinar moviendo 10 minutos.",
                "Endulzar con miel al final."
            )
        ),

        HistoricalRecipe(
            name = "Pan Medieval de Centeno",
            shortDescription = "Pan denso y oscuro típico del norte de Europa",
            type = "Pan", // Tipo
            rating = "⭐ 4.3",
            calories = "195 kcal por rebanada",
            history = "En la Edad Media el pan de centeno era el alimento principal en regiones frías donde el trigo no crecía. Monasterios europeos registran esta receta desde el siglo X.",
            ingredients = listOf(
                "2 tazas de harina de centeno",
                "1 taza de agua tibia",
                "1 cucharadita de sal",
                "½ cucharadita de levadura"
            ),
            preparation = listOf(
                "Mezclar los ingredientes.",
                "Amasar ligeramente.",
                "Reposar 1 hora.",
                "Formar un pan y hornear 45 minutos a 200°C."
            )
        ),

        HistoricalRecipe(
            name = "Sopa Rusa Shchi Medieval",
            shortDescription = "Sopa de col muy popular en la Rus de Kiev",
            type = "Sopa", // Tipo
            rating = "⭐ 4.4",
            calories = "160 kcal por porción",
            history = "Shchi aparece en documentos del siglo IX. Era consumida por campesinos y nobles, variando solo en calidad de ingredientes.",
            ingredients = listOf(
                "2 tazas de col picada",
                "1 zanahoria",
                "1 cebolla",
                "1 papa",
                "1 litro de agua",
                "Sal"
            ),
            preparation = listOf(
                "Cortar verduras.",
                "Hervir la papa 10 minutos.",
                "Agregar col, zanahoria y cebolla.",
                "Cocinar 20 minutos más.",
                "Salpimentar."
            )
        ),

        HistoricalRecipe(
            name = "Guiso Vikingo de Cordero",
            shortDescription = "Estofado de carne con cebolla y cebada",
            type = "Plato Fuerte", // Tipo
            rating = "⭐ 4.6",
            calories = "420 kcal por porción",
            history = "Restos arqueológicos y sagas nórdicas describen guisos de cordero combinados con cebada y hierbas silvestres entre los pueblos vikingos.",
            ingredients = listOf(
                "500g de cordero",
                "1 cebolla grande",
                "½ taza de cebada",
                "2 tazas de agua",
                "Sal y eneldo"
            ),
            preparation = listOf(
                "Cortar la carne en trozos.",
                "Dorarla en una olla.",
                "Agregar cebolla y cocinarla.",
                "Añadir cebada y agua.",
                "Hervir 45 minutos.",
                "Sazonar con eneldo."
            )
        ),

        HistoricalRecipe(
            name = "Machica Inca",
            shortDescription = "Harina tostada de cebada mezclada con agua o miel",
            type = "Bebida", // Tipo
            rating = "⭐ 4.1",
            calories = "220 kcal por porción",
            history = "La machica aparece en crónicas del siglo XVI como alimento portátil de los mensajeros chasquis. Se preparaba tostando cebada y moliéndola.",
            ingredients = listOf(
                "1 taza de harina de cebada tostada",
                "1 taza de agua o leche",
                "2 cucharadas de miel"
            ),
            preparation = listOf(
                "Calentar el líquido.",
                "Agregar harina y mezclar.",
                "Endulzar con miel.",
                "Cocinar 5 minutos."
            )
        ),

        HistoricalRecipe(
            name = "Pozole Prehispánico",
            shortDescription = "Guiso ritual de maíz cacahuazintle",
            type = "Sopa", // Tipo
            rating = "⭐ 4.8",
            calories = "350 kcal por plato",
            history = "El pozole tiene origen antes de la conquista. Se usaba en ceremonias mexicas dedicadas a Xipe Tótec. Los ingredientes eran maíz nixtamalizado, hierbas y carne de guajolote.",
            ingredients = listOf(
                "2 tazas de maíz cacahuazintle",
                "500g de carne de guajolote o pollo",
                "1 cebolla",
                "Orégano"
            ),
            preparation = listOf(
                "Remojar y hervir el maíz hasta que floree.",
                "Agregar la carne y cebolla.",
                "Cocinar 1 hora.",
                "Sazonar con orégano."
            )
        ),

        HistoricalRecipe(
            name = "Gallo Celta Hervido",
            shortDescription = "Plato ritual de tribus celtas",
            type = "Plato Fuerte", // Tipo
            rating = "⭐ 3.9",
            calories = "300 kcal por porción",
            history = "Los celtas cocinaban gallo en agua con hierbas aromáticas en banquetes religiosos, según registros romanos como los de Julio César.",
            ingredients = listOf(
                "1 gallo o pollo pequeño",
                "1 puñado de perejil",
                "1 puerro",
                "Sal",
                "Agua suficiente"
            ),
            preparation = listOf(
                "Colocar el gallo en una olla grande.",
                "Agregar hierbas y puerro.",
                "Cubrir con agua.",
                "Hervir 1 hora.",
                "Ajustar sal."
            )
        ),
        HistoricalRecipe(
            name = "Pan Plano Judío (Matzá)",
            shortDescription = "Pan sin levadura tradicional del antiguo Israel",
            type = "Pan", // Tipo
            rating = "⭐ 4.5",
            calories = "170 kcal por pieza",
            history = "El matzá es uno de los alimentos más antiguos documentados en textos hebreos. Representa la prisa con la que los israelitas huyeron de Egipto, sin tiempo para fermentar el pan. En la antigüedad se elaboraba simplemente con harina y agua, cocido en hornos de barro o planchas calientes.",
            ingredients = listOf(
                "2 tazas de harina de trigo",
                "½ taza de agua",
                "1 cucharadita de sal",
                "1 cucharada de aceite (opcional)"
            ),
            preparation = listOf(
                "Precalentar sartén o comal a fuego medio.",
                "Mezclar la harina con la sal en un bowl.",
                "Agregar el agua poco a poco.",
                "Amasar hasta obtener una masa uniforme.",
                "Formar una bola y dejar reposar 5 minutos.",
                "Dividir la masa en 6 porciones iguales.",
                "Estirar cada porción en discos delgados.",
                "Pinchar los discos con un tenedor para evitar burbujas.",
                "Colocar un disco en el comal caliente.",
                "Cocinar 1–2 minutos hasta ver puntos dorados.",
                "Voltear y cocinar del otro lado.",
                "Repetir con todos los discos.",
                "Mantenerlos cubiertos con un paño para conservar suavidad.",
                "Servir calientes.",
                "Opcional: untar un poco de aceite al final para añadir sabor."
            )
        ),
        HistoricalRecipe(
            name = "Avena Vikinga (Hafragrautur)",
            shortDescription = "Gachas de avena consumidas por pueblos nórdicos",
            type = "Plato Fuerte", // Tipo
            rating = "⭐ 4.4",
            calories = "290 kcal por porción",
            history = "La avena era uno de los granos esenciales en la dieta vikinga. Las sagas nórdicas mencionan su consumo diario, especialmente en climas fríos. Se preparaba con agua o leche, y ocasionalmente se endulzaba con miel cuando estaba disponible.",
            ingredients = listOf(
                "1 taza de avena tradicional",
                "2 tazas de agua o leche",
                "1 pizca de sal",
                "1 cucharada de miel",
                "1 cucharadita de mantequilla (opcional)"
            ),
            preparation = listOf(
                "Colocar agua o leche en una olla.",
                "Agregar una pizca de sal.",
                "Llevar a ebullición.",
                "Agregar la avena lentamente.",
                "Revolver para evitar que se pegue.",
                "Bajar a fuego medio.",
                "Cocinar por 7–10 minutos.",
                "Mezclar ocasionalmente para dar cremosidad.",
                "Si se espesa demasiado, agregar un poco más de líquido.",
                "Retirar del fuego.",
                "Añadir miel para endulzar.",
                "Agregar mantequilla para dar mayor cuerpo.",
                "Servir caliente.",
                "Opcional: añadir frutos secos si deseas un toque moderno.",
                "Consumir como desayuno energético al estilo nórdico."
            )
        ),
        HistoricalRecipe(
            name = "Ensalada Romana de Pepino (Cucumeres)",
            shortDescription = "Ensalada fresca mencionada en textos romanos",
            type = "Ensalada", // Tipo
            rating = "⭐ 4.3",
            calories = "90 kcal por porción",
            history = "Según el escritor romano Columela, los pepinos eran muy apreciados y se consumían frescos o en vinagre. Las ensaladas simples con vinagre, sal y hierbas eran comunes en las casas romanas y en los campamentos militares.",
            ingredients = listOf(
                "2 pepinos medianos",
                "2 cucharadas de vinagre de vino",
                "1 cucharada de aceite de oliva",
                "1 cucharadita de menta fresca picada",
                "Sal y pimienta"
            ),
            preparation = listOf(
                "Lavar y pelar los pepinos.",
                "Cortar en rodajas delgadas.",
                "Colocarlos en un tazón grande.",
                "Agregar el vinagre de vino.",
                "Añadir el aceite de oliva.",
                "Incorporar la menta picada.",
                "Sazonar con sal.",
                "Agregar un toque de pimienta.",
                "Mezclar bien con movimientos suaves.",
                "Refrigerar 10 minutos para intensificar sabores.",
                "Servir fría.",
                "Opcional: añadir un toque extra de menta al final.",
                "Ideal como acompañamiento romano clásico."
            )
        ),
        HistoricalRecipe(
            name = "Guiso Medieval de Garbanzos",
            shortDescription = "Estofado típico europeo del siglo XIV",
            type = "Guiso", // Tipo
            rating = "⭐ 4.6",
            calories = "350 kcal por porción",
            history = "Registros del Libro de Cocina de Taillevent (siglo XIV) mencionan caldos espesos de garbanzo con hierbas y verduras. Este tipo de guiso sustentó a campesinos y viajeros en Europa medieval.",
            ingredients = listOf(
                "1 taza de garbanzos remojados",
                "1 cebolla grande",
                "2 zanahorias",
                "2 tallos de apio",
                "2 dientes de ajo",
                "1 hoja de laurel",
                "1 cucharadita de tomillo",
                "4 tazas de agua",
                "1 cucharada de aceite",
                "Sal"
            ),
            preparation = listOf(
                "Escurrir los garbanzos remojados.",
                "Picar cebolla, zanahoria y apio.",
                "Calentar aceite en una olla.",
                "Sofreír cebolla hasta que se vea transparente.",
                "Agregar zanahorias y apio.",
                "Añadir el ajo machacado.",
                "Incorporar los garbanzos.",
                "Verter las 4 tazas de agua.",
                "Agregar la hoja de laurel.",
                "Añadir tomillo.",
                "Tapar y cocinar a fuego medio 45–60 minutos.",
                "Revolver ocasionalmente.",
                "Retirar la hoja de laurel.",
                "Sazonar con sal al gusto.",
                "Servir caliente con pan rústico."
            )
        ),
        HistoricalRecipe(
            name = "Guiso Sumerio de Cebada",
            shortDescription = "Plato basado en tablillas de la antigua Mesopotamia",
            type = "Guiso", // Tipo
            rating = "⭐ 4.2",
            calories = "330 kcal por porción",
            history = "Una de las primeras recetas escritas en la historia proviene de tablillas sumerias. Usaban cebada hervida con vegetales simples y aceite de sésamo.",
            ingredients = listOf(
                "1 taza de cebada perlada",
                "3 tazas de agua",
                "1 cebolla picada",
                "1 cucharada de aceite de sésamo",
                "1 zanahoria",
                "1 pizca de cilantro seco",
                "Sal"
            ),
            preparation = listOf(
                "Enjuagar la cebada.",
                "Colocarla en una olla con agua.",
                "Hervir 20 minutos.",
                "Agregar la cebolla picada.",
                "Añadir la zanahoria en cubos.",
                "Incorporar el aceite de sésamo.",
                "Agregar cilantro seco.",
                "Revolver suavemente.",
                "Tapar y cocinar 20 minutos más.",
                "Sazonar con sal.",
                "Servir caliente como en la antigua Mesopotamia."
            )
        ),
        HistoricalRecipe(
            name = "Hummus Antiguo",
            shortDescription = "Pasta de garbanzo consumida desde hace siglos",
            type = "Dip/Acompañamiento", // Tipo
            rating = "⭐ 4.8",
            calories = "260 kcal por porción",
            history = "El hummus aparece en textos árabes del siglo XIII y probablemente existía desde antes en variantes más simples sin tantos condimentos modernos.",
            ingredients = listOf(
                "1 taza de garbanzos cocidos",
                "2 cucharadas de tahini",
                "1 diente de ajo",
                "2 cucharadas de jugo de limón",
                "2 cucharadas de aceite de oliva",
                "Sal"
            ),
            preparation = listOf(
                "Colocar garbanzos cocidos en un procesador.",
                "Agregar tahini.",
                "Incorporar el diente de ajo.",
                "Agregar limón.",
                "Verter aceite.",
                "Sazonar con sal.",
                "Procesar todo hasta obtener una pasta suave.",
                "Rectificar sal.",
                "Servir con aceite encima."
            )
        ),
        HistoricalRecipe(
            name = "Sopa Egipcia de Lentejas Rojas (Shorbat Ads)",
            shortDescription = "Plato tradicional de Egipto consumido desde tiempos faraónicos",
            type = "Sopa", // Tipo
            rating = "⭐ 4.7",
            calories = "280 kcal por porción",
            history = "Las lentejas eran uno de los cultivos más importantes del Antiguo Egipto. Las tumbas de Saqqara y los papiros médicos mencionan caldos nutritivos de lentejas combinados con cebolla y comino. Hoy, la Shorbat Ads es la heredera directa de esa tradición.",
            ingredients = listOf(
                "1 taza de lentejas rojas",
                "1 cebolla picada",
                "2 dientes de ajo",
                "1 zanahoria",
                "1 cucharadita de comino",
                "1 cucharadita de cúrcuma",
                "4 tazas de agua o caldo",
                "1 cucharada de aceite",
                "Sal y limón"
            ),
            preparation = listOf(
                "Lavar las lentejas rojas bajo el agua.",
                "Calentar aceite en una olla.",
                "Sofreír cebolla hasta que esté transparente.",
                "Agregar el ajo machacado.",
                "Añadir la zanahoria en cubos.",
                "Agregar las lentejas lavadas.",
                "Verter el agua o caldo.",
                "Añadir comino y cúrcuma.",
                "Mezclar bien y tapar la olla.",
                "Cocinar 20 minutos a fuego medio.",
                "Revisar textura y agregar agua si es necesario.",
                "Retirar del fuego.",
                "Licuar completamente (opcional para textura suave).",
                "Sazonar con sal.",
                "Servir caliente con un chorrito de limón.",
                "Acompañar con pan plano estilo egipcio."
            )
        ),
        HistoricalRecipe(
            name = "Tortas Romanas de Queso y Miel (Libum)",
            shortDescription = "Panecillos dulces ofrecidos en rituales romanos",
            type = "Postre", // Tipo
            rating = "⭐ 4.6",
            calories = "240 kcal por pieza",
            history = "El 'Libum' aparece en el libro De Agricultura de Catón el Viejo. Era una torta ofrecida a los dioses en celebraciones domésticas. Consistía en queso fresco, harina y miel, cocido lentamente.",
            ingredients = listOf(
                "1 taza de queso fresco triturado",
                "1 huevo",
                "½ taza de harina",
                "1 cucharada de miel",
                "1 pizca de sal"
            ),
            preparation = listOf(
                "Triturar el queso fresco con un tenedor.",
                "Agregar el huevo y mezclar.",
                "Añadir la harina hasta formar una masa suave.",
                "Agregar una pizca de sal.",
                "Formar bolitas pequeñas.",
                "Aplastar ligeramente cada bolita.",
                "Colocar en una charola engrasada.",
                "Hornear a 180°C durante 25 minutos.",
                "Retirar cuando estén doradas.",
                "Dejar reposar 5 minutos.",
                "Bañar con miel tibia.",
                "Servir como postre romano tradicional."
            )
        ),
        HistoricalRecipe(
            name = "Kheer Indio Antiguo",
            shortDescription = "Arroz dulce con leche usado en festivales desde hace milenios",
            type = "Postre", // Tipo
            rating = "⭐ 4.8",
            calories = "320 kcal por porción",
            history = "El Kheer aparece en textos sánscritos del 400 a.C. Era un postre ceremonial preparado con arroz, leche y azúcar de palma. En la India antigua se consideraba un alimento auspicioso.",
            ingredients = listOf(
                "½ taza de arroz",
                "4 tazas de leche",
                "¼ taza de azúcar",
                "1 cucharadita de cardamomo",
                "1 cucharada de almendras picadas",
                "1 cucharada de pasas"
            ),
            preparation = listOf(
                "Lavar el arroz hasta que el agua salga clara.",
                "Colocar la leche en una olla grande.",
                "Calentar la leche a fuego medio.",
                "Agregar el arroz lavado.",
                "Revolver constantemente para evitar que se pegue.",
                "Cocinar 20 minutos hasta que el arroz ablande.",
                "Agregar el azúcar.",
                "Añadir cardamomo.",
                "Agregar pasas.",
                "Continuar mezclando hasta espesar.",
                "Retirar del fuego.",
                "Añadir almendras picadas.",
                "Servir caliente o frío según preferencia.",
                "Dejar reposar para que tome textura cremosa."
            )
        ),
        HistoricalRecipe(
            name = "Sopa China de Jengibre y Pollo",
            shortDescription = "Caldo medicinal tradicional desde la dinastía Han",
            type = "Sopa", // Tipo
            rating = "⭐ 4.4",
            calories = "210 kcal por porción",
            history = "Los escritos médicos del emperador amarillo (Huangdi Neijing) mencionan caldos ligeros con jengibre y pollo para equilibrar el Qi. Era uno de los platos de recuperación más comunes en la antigua China.",
            ingredients = listOf(
                "200 g de pollo en cubos",
                "1 litro de agua",
                "1 trozo de jengibre de 3 cm",
                "2 cebollitas cambray",
                "1 cucharada de salsa de soya ligera",
                "Sal"
            ),
            preparation = listOf(
                "Hervir el litro de agua en una olla.",
                "Agregar el pollo en cubos.",
                "Espumar la superficie para limpiar el caldo.",
                "Agregar el jengibre en rodajas.",
                "Picar las cebollitas cambray.",
                "Añadir la parte blanca al caldo.",
                "Tapar y cocinar 25 minutos.",
                "Agregar salsa de soya ligera.",
                "Revolver suavemente.",
                "Añadir una pizca de sal.",
                "Cocinar 5 minutos más.",
                "Servir con la parte verde de las cebollitas encima.",
                "Consumir caliente como caldo medicinal tradicional."
            )
        ),
        HistoricalRecipe(
            name = "Estofado Irlandés Antiguo",
            shortDescription = "Guiso de cordero y raíces usado desde la Edad Media",
            type = "Guiso", // Tipo
            rating = "⭐ 4.6",
            calories = "380 kcal por porción",
            history = "El Irish Stew aparece en registros del siglo XIV. Era un plato rústico hecho con cordero, papas y cebolla. Representaba la cocina campesina irlandesa.",
            ingredients = listOf(
                "300 g de cordero en cubos",
                "2 papas medianas",
                "1 cebolla grande",
                "2 zanahorias",
                "3 tazas de agua",
                "1 cucharadita de tomillo",
                "Sal y pimienta"
            ),
            preparation = listOf(
                "Picar cebolla y zanahorias.",
                "Cortar papas en trozos.",
                "Colocar el cordero en una olla.",
                "Agregar cebolla y zanahorias.",
                "Añadir las papas.",
                "Verter el agua.",
                "Agregar tomillo.",
                "Tapar y cocinar 45 minutos.",
                "Revolver ocasionalmente.",
                "Sazonar con sal.",
                "Añadir pimienta.",
                "Cocinar 10 minutos más.",
                "Servir caliente con pan oscuro."
            )
        ),
        HistoricalRecipe(
            name = "Pan Plano Persa (Nan-e Barbari)",
            shortDescription = "Pan tradicional del antiguo Imperio Persa",
            type = "Pan", // Tipo
            rating = "⭐ 4.7",
            calories = "210 kcal por unidad",
            history = "Este pan tiene raíces en el periodo Safávida. Su elaboración con harina, agua y semillas era común en caravasares (posadas comerciales).",
            ingredients = listOf(
                "2 tazas de harina",
                "1 cucharadita de levadura",
                "1 cucharadita de sal",
                "¾ taza de agua tibia",
                "1 cucharada de ajonjolí"
            ),
            preparation = listOf(
                "Mezclar harina, sal y levadura.",
                "Agregar agua tibia poco a poco.",
                "Amasar hasta obtener una masa suave.",
                "Cubrir y reposar 1 hora.",
                "Formar una tira alargada.",
                "Hacer líneas con los dedos.",
                "Espolvorear ajonjolí.",
                "Hornear a 200°C durante 18 minutos.",
                "Servir tibio."
            )
        ),
        HistoricalRecipe(
            name = "Sopa Azteca de Maíz (Pozolli Simple)",
            shortDescription = "Versión antigua del pozole prehispánico",
            type = "Sopa", // Tipo
            rating = "⭐ 4.5",
            calories = "310 kcal por porción",
            history = "El pozolli aparece en crónicas de fray Bernardino de Sahagún. Se preparaba con maíz nixtamalizado, hierbas locales y ocasionalmente carne.",
            ingredients = listOf(
                "2 tazas de maíz precocido",
                "1 diente de ajo",
                "1 trozo pequeño de cebolla",
                "1 cucharadita de orégano",
                "1 pechuga de pollo",
                "4 tazas de agua",
                "Sal"
            ),
            preparation = listOf(
                "Hervir agua en una olla.",
                "Agregar el maíz precocido.",
                "Añadir ajo y cebolla.",
                "Cocinar 30 minutos.",
                "Agregar la pechuga de pollo.",
                "Tapar y cocinar 20 minutos más.",
                "Retirar la pechuga y deshebrar.",
                "Regresar el pollo a la olla.",
                "Agregar orégano.",
                "Sazonar con sal.",
                "Servir caliente.",
                "Acompañar con limón si se desea."
            )
        ),
        HistoricalRecipe(
            name = "Sopa Japonesa de Miso Antiguo",
            shortDescription = "Caldo fermentado tradicional del Japón feudal",
            type = "Sopa", // Tipo
            rating = "⭐ 4.8",
            calories = "160 kcal por porción",
            history = "El miso tiene más de 1,300 años de historia. Monjes budistas lo preparaban como alimento energético y medicinal. Esta versión refleja una preparación simple, sin ingredientes modernos raros.",
            ingredients = listOf(
                "1 litro de agua",
                "2 cucharadas de miso",
                "1 cebollita cambray",
                "100 g de tofu firme",
                "1 lámina pequeña de alga nori"
            ),
            preparation = listOf(
                "Calentar el litro de agua en una olla.",
                "Cortar tofu en cubos.",
                "Agregar el tofu cuando el agua esté caliente.",
                "Cortar cebollita cambray.",
                "Añadir la parte blanca al caldo.",
                "Retirar un cucharón de caldo.",
                "Disolver el miso en el caldo aparte.",
                "Verter la mezcla de miso a la olla.",
                "No dejar hervir para preservar el sabor.",
                "Agregar la parte verde de la cebollita.",
                "Cortar tiras pequeñas de nori.",
                "Añadir al caldo.",
                "Servir caliente."
            )
        ),
    )
}