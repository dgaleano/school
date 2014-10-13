program spinner;

uses
        crt;

{ ========================================================================= }
{ delayRotate - makes a spinning effect for a specified amound of rotations }
{ ========================================================================= }
procedure delayRotate(rotations,randMax,x,y:integer);
// rotations: how many full rotations
// randMax: maximum random milliseconds between each movement
// x & y : position of spinning thing

var
        count, i : integer;
begin

        count:=0; // start count at 0
        repeat
                begin;
                randomize; // enable random()
                count:=count+1; // each time 1 is added on

                window(10,10,80,24);
                textbackground(blue);

                delay(random(randMax)); // random number upto randMax
                //gotoxy(x,y);
                write('- Lo     ');
                delay(random(randMax));
                //gotoxy(x,y);
                write('\ Load   ');
                delay(random(randMax));
                //gotoxy(x,y);
                write('| Loadin ');
                delay(random(randMax));
                //gotoxy(x,y);
                write('/ Loading');
                delay(random(randMax));

                end;
        until (count=rotations); // repeat untill count = dots
        gotoxy(x,y);
        write('Loading complete!');

end;

begin
delayRotate(10,500,40,10);
writeln;
gotoxy(36,10);
write('The end! :D');
readln;
end.
