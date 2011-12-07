set key off
set term jpeg
set out "img/VariacionItBLComplejidad.jpg"
set title "Cantidad de operaciones de las soluciones con respecto a la variación \n de la cantidad de iteraciones de búsqueda local" 
set xlabel "Cantidad de iteraciones de Búsqueda Local testeado" 
set ylabel "Cantidad de operaciones" 
set zlabel "" 
unset logscale x 
unset logscale y 
unset logscale z 
plot [:] [:] [:] './DataParaGraficos/m1_bl' using 4:5  with linespoints     title "mediciones " , './DataParaGraficos/m2_bl' using 4:5  with linespoints     title "mediciones " , './DataParaGraficos/m3_bl' using 4:5  with linespoints     title "mediciones " , './DataParaGraficos/m4_bl' using 4:5  with linespoints     title "mediciones " , './DataParaGraficos/m5_bl' using 4:5  with linespoints     title "mediciones " , './DataParaGraficos/m6_bl' using 4:5  with linespoints     title "mediciones " , './DataParaGraficos/m7_bl' using 4:5  with linespoints     title "mediciones " , './DataParaGraficos/m8_bl' using 4:5  with linespoints     title "mediciones " , './DataParaGraficos/m9_bl' using 4:5  with linespoints     title "mediciones 9 "  

