program pinNumber;

var
pin1, pin2, pin3, pin4, finalpin : integer;
endapp : char;

begin

clrscr;
writeln('Welcome to nicks bank');
write('Please enter your pin number:');
write('  ');
read(pin1);
write('  ');
read(pin2);
write('  ');
read(pin3);
write('  ');
read(pin4);
write('  ');
finalpin = pin1 + pin2 + pin3 + pin4;
IF read='1234' THEN writeLn('Pin OK!');
ELSE wrieln('Get lost')
readLn(endapp);
end.

