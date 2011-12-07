set key off
set term jpeg
set out "img/VariacionAlfaComplejidad.jpg"
set title "Cantidad de operaciones de las soluciones\n con respecto a la variación de ALFA" 
set xlabel "ALFA Testeado" 
set ylabel "Cantidad de operaciones" 
set zlabel "" 
unset logscale x 
unset logscale y 
unset logscale z 
plot [:] [:] [:] './DataParaGraficos/m1_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m2_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m3_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m4_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m5_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m6_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m7_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m8_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m9_alfa' using 3:4  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución "  

