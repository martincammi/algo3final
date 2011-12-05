set title "" 
set term jpeg
set out "img/CalculoComplejidad.jpg"
set xlabel "Tamaño de entrada en bits" 
set ylabel "Cantidad de operaciones\nEscala Logarítmica" 
set zlabel ""
unset logscale x 
set logscale y 
unset logscale z 
plot [:] [:] [:] './DataParaGraficos/ALFA_0.1_CANT_ITERACIONES_MAXIMA_200_CANT_ITERACIONES_SIN_MEJORAR_30_CANT_ITERACIONES_BUSQUEDA_LOCAL_50.dataset' using 4:5  with linespoints     title "Mediciones " , x**(3)*1/500000 with lines  title 'T^3 * 1/500000'   
