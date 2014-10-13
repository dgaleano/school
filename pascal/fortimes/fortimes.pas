program fortimes;

var
        loop,table:   integer;

begin
        write('Which times table would you like?');
        readln(table);
        for loop := 1 to 12 do
                writeln(loop:4,' times ',table:2,' = ',loop * table:4);
        readln;

end.
