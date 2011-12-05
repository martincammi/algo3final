set key off
set term jpeg
set out "img/VariacionItSinMejora.jpg"
set title "" 
set xlabel "Max iteraciones sin mejora testeado" 
set ylabel "Peso agregado (%)\nRespecto a las aristas originales"
set zlabel ""
unset logscale x 
unset logscale y 
unset logscale z 
plot [:] [:] [:] './DataParaGraficos/m1_itsinmejora' using 2:5  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m2_itsinmejora' using 2:5  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m3_itsinmejora' using 2:5  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m4_itsinmejora' using 2:5  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m5_itsinmejora' using 2:5  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m6_itsinmejora' using 2:5  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m7_itsinmejora' using 2:5  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m8_itsinmejora' using 2:5  with linespoints  pointsize 2   title "Iteraciones sin mejorar maximo " , './DataParaGraficos/m9_itsinmejora' using 2:5  with linespoints     title "Iteraciones sin mejorar maximo "  

