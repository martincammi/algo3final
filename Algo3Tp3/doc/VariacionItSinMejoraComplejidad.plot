set key off
set term jpeg
set out "img/VariacionItSinMejoraComplejidad.jpg"
set title "Cantidad de operaciones de las soluciones con respecto a la variación \n de la cantidad de iteraciones sin mejorar" 
set xlabel "Max iteraciones sin mejora testeado" 
set ylabel "Cantidad de operaciones" 
set zlabel ""
unset logscale x 
unset logscale y 
unset logscale z 
plot [:] [:] [:] './DataParaGraficos/m1_itsinmejora' using 2:4  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m2_itsinmejora' using 2:4  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m3_itsinmejora' using 2:4  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m4_itsinmejora' using 2:4  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m5_itsinmejora' using 2:4  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m6_itsinmejora' using 2:4  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m7_itsinmejora' using 2:4  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m8_itsinmejora' using 2:4  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m9_itsinmejora' using 2:4  with linespoints     title "Iteraciones sin mejorar maximo "  

