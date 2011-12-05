set key off
set term jpeg
set out "img/VariacionAlfa.jpg"
set title "" 
set xlabel "ALFA Testeado" 
set ylabel "Peso agregado (%)\nRespecto a las aristas originales" 
set zlabel "" 
unset logscale x 
unset logscale y 
unset logscale z 
plot [:] [:] [:] './DataParaGraficos/m1_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m2_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m3_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m4_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m5_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m6_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m7_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m8_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución " , './DataParaGraficos/m9_alfa' using 3:5  with linespoints  pointsize 2   title "Mediciones de Calidad de Solución "  

