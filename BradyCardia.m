[RPeakIndices, RPeakValues] = Rpeak('ekg_raw_16483.dat');

RPeakIndices;
heart_rate = zeros(30);
x = zeros(30);
class = zeros(30);
for i = 1:30  
    ind = find(RPeakIndices >= (i-1)*7680 & RPeakIndices < i*7680);
    heart_rate(i) = length(ind);
    x(i) = i;
    if(heart_rate(i) < 60)
        class(i) = 1;
    else
        class(i) = 0;
    end
end
plot(heart_rate);
disp(heart_rate);
title('Heart Rate per minute');
xlabel('Minutes');
ylabel('Heart Rate');
