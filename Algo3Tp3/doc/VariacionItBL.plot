set key off
set term jpeg
set out "img/VariacionItBL.jpg"
set title "Calidad de las soluciones con respecto a la variación de\n la cantidad de iteraciones de búsqueda local" 
set xlabel "Cantidad de iteraciones de Búsqueda Local testeado" 
set ylabel "Peso agregado (%)\nRespecto a las aristas originales" 
set zlabel "" 
unset logscale x 
unset logscale y 
unset logscale z 
plot [:] [:] [:] './DataParaGraficos/m1_bl' using 4:6  with linespoints     title "mediciones " , './DataParaGraficos/m2_bl' using 4:6  with linespoints     title "mediciones " , './DataParaGraficos/m3_bl' using 4:6  with linespoints     title "mediciones " , './DataParaGraficos/m4_bl' using 4:6  with linespoints     title "mediciones " , './DataParaGraficos/m5_bl' using 4:6  with linespoints     title "mediciones " , './DataParaGraficos/m6_bl' using 4:6  with linespoints     title "mediciones " , './DataParaGraficos/m7_bl' using 4:6  with linespoints     title "mediciones " , './DataParaGraficos/m8_bl' using 4:6  with linespoints     title "mediciones " , './DataParaGraficos/m9_bl' using 4:6  with linespoints     title "mediciones "  

