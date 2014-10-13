program airbook;

uses
   strings, dos, crt;
type
    seat = record
        name : string;
        date : string;
    end;
var
   neverend, continue : boolean;
   indent : string;
   menusel : char;
   seats : array['A'..'G', 1..4] of seat;
   num : char;
   i : integer;
   j : char;
   pname : string;
   row : char;
   col : integer;
   yn : char;
   Year, Month, Day, DayOfWeek, Hour, Minute, Second, Sec100: longint;
label
   menu, exit;

procedure init;
begin;
for j := 'A' to 'G' do
      for i := 1 to 4 do
          seats[j,i].name := 'Vacant    ';
end;

procedure iwriteln(value:string);
begin
   writeln(indent,value);
end;

procedure iwrite(value:string);
begin
   write(indent,value);
end;

procedure checknum(num:char);
var
   validnum : boolean;
begin
validnum := false;
readln(num);
repeat
   if (ord(num) > 57) or (ord(num) < 48) then
      begin
         writeln;
         iwrite('Invalid number, please try again. ');
         readln(num);
      end
   else
      begin
          validnum := true;
      end;
until validnum;
i := ord(num) - 48;
end;

procedure checkchar(j:char);
var
   validchar : boolean;
begin
validchar := false;
readln(j);
repeat
   if (ord(j) < 58) and (ord(j) > 47) then
      begin
         writeln;
         iwrite('Invalid letter, please try again. ');
         readln(j);
      end
   else
      begin
          validchar := true;
      end;
until validchar;
j := upcase(j);
end;

procedure time;
const
   blankBg = '                                                   ';
begin
   GetDate(Year, Month, Day, DayOfWeek);
   GetTime(Hour, Minute, Second, Sec100);

   gotoxy(16,7);
   write('The current time is ');

   if (Hour<10) then
      write('0',Hour:1, ':') // add leading zero
   else
      write(Hour:2, ':');

   if (Minute<10) then
      write('0',Minute:1,' on ') // add leading zero
   else
      write(Minute:2,' on ');

   case DayOfWeek of // replace number with name
      0: write('Sunday');
      1: write('Monday');
      2: write('Tuesday');
      3: write('Wednesday');
      4: write('Thursday');
      5: write('Friday');
      6: write('Saturday');
   end;

   write(', ', Day, '/', Month, '/', Year:4, ' ');
   writeln;
end;


procedure pageTitle;
var
   tcolor, fgcolor, bgcolor : byte;

const
   x=25; // space away from side
   y=2; // space away from top
   line1=('       Transman Airlines     ');
   line2=('   Airline Booking Systems   ');
   blank=('                             '); // goes at top and bottom of title text

begin
   fgcolor:=blue;
   bgcolor:=black;
   tcolor:=white;

   clrscr; // clears screen every time title is shown
   writeln;
         textcolor(tcolor);
   gotoxy(x,y); // starting point
         textbackground(fgcolor);
   write(blank);
   writeln;
      textbackground(bgcolor);
   gotoxy(x,y+1); // move down 1 row
      textbackground(fgcolor);
   write(line1);
   writeln;
      textbackground(bgcolor);
   gotoxy(x,y+2); // move down 1 row
      textbackground(fgcolor);
   write(line2);
   writeln;
      textbackground(bgcolor);
   gotoxy(x,y+3); // move down 1 row
      textbackground(fgcolor);
   write(blank);
      textbackground(bgcolor);
      textcolor(tcolor);
   writeln;
   writeln;
   time; // shows the time after the title
   writeln;
   indent:='   ';
end;

{ ========================================================================= }
{ delayRotate - makes a spinning effect for a specified amound of rotations }
{ ========================================================================= }
procedure delayRotate(rotations,randMax,x,y:integer);
// rotations: how many full rotations
// randMax: maximum random milliseconds between each movement
// x & y : position of spinning effect

var
        count, i : integer;
begin
        count:=0; // start count at 0

        // draw a background
        textbackground(blue);
        gotoxy(x-3,y-2);
        write('|============|');
        gotoxy(x-3,y-1);
        write('|            |');
        gotoxy(x-3,y);
        write('|            |');
        gotoxy(x-3,y+1);
        write('|            |');
        gotoxy(x-3,y+2);
        write('|============|');

        // start repeating rotations
        repeat
                begin;
                randomize; // enable random()
                count:=count+1; // each time 1 is added on

                delay(random(randMax)); // random number upto randMax
                gotoxy(x,y);
                write('- B    ');
                delay(random(randMax));
                gotoxy(x,y);
                write('\ Bu   ');
                delay(random(randMax));
                gotoxy(x,y);
                write('| Bus  ');
                delay(random(randMax));
                gotoxy(x,y);
                write('/ Busy ');
                delay(random(randMax));

                end;
        until (count=rotations); // repeat untill count = dots

        //gotoxy(x,y);
        //write('All Done!');
        //delay(600);
        textbackground(black);
end;

procedure mainmenu;
begin
   pageTitle;
   indent:='                     ';
   iwriteln('[          - Main Menu -         ]');
   writeln;
   iwriteln('[ (V)iew current seating status. ]');
   iwriteln('[ (M)ake a booking.              ]');
   iwriteln('[ (E)dit a booking.              ]');
   iwriteln('[ (D)elete a booking.            ]');
   iwriteln('[ Delete (A)ll bookings.         ]');
   iwriteln('[ E(x)it Program                 ]');
   writeln;
   iwriteln('[ Please select a letter: ( )    ]');
   iwrite  ('[ Then press enter!              ]');
   gotoxy(49,18);
   readln(menusel);
   delayRotate(2,200,35,15);
end;

procedure confirm(conf:string);
begin;
   iwrite('Are you sure you want to ');
   write(conf, '? (Y/N) ');
   readln(yn);
end;

procedure drawTable;
begin;
for i := 1 to 2 do
      write (i:13,'   ');

   write('       ');

   for i := 3 to 4 do
      write (i:13,'   ');

   writeln;

   write('     ');
   for i := 1 to 4 do
       write ('===============','===');

   writeln;

   for j := 'A' to 'G' do
      begin
         iwrite(j);
         write(' | ');
         for i := 1 to 2 do
            write (seats[j,i].name:13,' | ');

         write('     | ');
         for i := 3 to 4 do
            write (seats[j,i].name:13,' | ');
         writeln;
      end;

   write('     ');
   for i := 1 to 4 do
       write ('===============','===');
end;

procedure viewBooking;
begin;
   pageTitle;
   indent:='   ';
   iwriteln('Viewing current booking status.');
   writeln;
   drawTable;
   writeln;
   writeln;
   iwrite('Press enter to contiunue...');
   readln;
end;

procedure deleteBooking;
begin;
   pageTitle;
   indent:='   ';
   iwriteln('Delete a booking');
   writeln;
   writeln;
   drawTable;
   writeln;
   writeln;
   iwrite('Please enter the ROW (A to G): ');
   readln(j);
   writeln;
   writeln;
   iwrite('Please enter the COLUMN (1 to 4): ');
   readln(i);
   writeln;
   writeln;
   iwrite('');
   write('You selected Row/Column ', upCase(j), i, '. Is this correct? (Y/N) ');
   readln(yn);
   if(yn='Y') or (yn='y') then
      begin;
         seats[j,i].name := 'Vacant    ';
         writeln;
         iwrite('');
         write('Booking ',i,upCase(j),' has been deleted!');
         writeln;
         iwrite('Press enter to contiunue...');
         readln;
      end
   else
      deleteBooking;
end;

procedure makeBooking;
label enterpname, checkseat, back_to_mainmenu;
var
   seatused, error: boolean;
   opt : char;
begin;
   pageTitle;
   indent:='   ';
   error:=false;
   iwrite('Make a booking');
   writeln;
   writeln;
   drawTable;
   writeln;
   writeln;
   iwrite('Please enter the ROW (A to G): ');
   checkchar(j);
   writeln;
   iwrite('Please enter the COLUMN (1 to 4): ');
   checknum(num);
   writeln;
   iwrite('');
   write('You selected Row/Column ', j, i, '. Is this correct? (Y/N) ');
   readln(yn);

   checkseat:
   if ( seats[j,i].name = '') then seatused:=false else seatused:=true;
   continue:=true;
   
   if(seatused=true) then
      begin;
         pageTitle;
         indent:='   ';
         iwriteln('Sorry, the seat you requested has already been booked!');
         writeln;
         iwriteln(' * (D)elete booking.');
         iwriteln(' * Select (a)nother another seat.');
         iwriteln(' * E(x)it to main menu.');
         writeln;
         iwriteln('Please choose either A, D or X [ ] from above, then press enter! ');
         writeln;
         
         if error then
         // If a bad char has been input
            begin;
               textColor(yellow);
               iwriteln('You have entered an invalid option, please try again!');
               textColor(white);
            end;
         
         gotoxy(36,15);
         readln(opt);
         // Move curser between square brackets
         
         if (opt='a') or (opt='A') then
         // If char input: retry
            begin
               continue:=false;
               makeBooking;
            end;
         if (opt='d') or (opt='D') then
	 // If char input: delete
            begin
               continue:=true;
               deleteBooking;
            end;
         if (opt='x') or (opt='X') then
	 // If char input: goto main menu
            begin
               continue:=false;
            end
         else // Bad char input
            begin;
               error:=true;
               goto checkseat;
            end;
      end;

   if ((yn='Y') or (yn='y')) and continue then
      begin;
         enterpname:
	 writeln;
         iwrite('Please enter the name of the paseneger: ');
         readln(pname);
	 writeln;
	 iwrite('');
	 write('You entered ', pname, ' as the passeneger, is this correct? (Y/N) ');
         readln(yn);
      	 if(yn='Y') or (yn='y') then
            begin;
            seats[upcase(j),i].name := pname;
            writeln;
            iwrite('');
            writeln('Booking ', i, j,' (under ',pname,') has been made!');
            writeln;
            iwrite('Press enter to contiunue...');
            readln;
         end
         else
            goto enterpname;
      end
   else
      if continue then makeBooking;
end;

procedure editBooking;
label enterpname, checkseat, back_to_mainmenu;
var
   seatused, error: boolean;
   opt : char;
begin;
   pageTitle;
   indent:='   ';
   error:=false;
   iwrite('Edit a booking');
   writeln;
   writeln;
   drawTable;
   writeln;
   writeln;
   iwrite('Please enter the ROW (A to G): ');
   checkchar(j);
   writeln;
   iwrite('Please enter the COLUMN (1 to 4): ');
   checknum(num);
   writeln;
   iwrite('');
   write('You selected Row/Column ', j, i, '. Is this correct? (Y/N) ');
   readln(yn);

   if ((yn='Y') or (yn='y')) and continue then
      begin;
         enterpname:
	 writeln;
         iwrite('Please enter the name of the paseneger: ');
         readln(pname);
	 writeln;
	 iwrite('');
	 write('You entered ', pname, ' as the passeneger, is this correct? (Y/N) ');
         readln(yn);
      	 if(yn='Y') or (yn='y') then
            begin;
            seats[upcase(j),i].name := pname;
            writeln;
            iwrite('');
            writeln('Booking ', i, j,' (under ',pname,') has been made!');
            writeln;
            iwrite('Press enter to contiunue...');
            readln;
         end
         else
            goto enterpname;
      end
   else
      if continue then makeBooking;
end;

procedure deleteAllBookings;
begin;
   pageTitle;
   indent:='   ';
   confirm('delete all bookings?');
   if (yn='Y') or (yn='y') then
      begin;
         init;
	 
         writeln;
         iwriteln('All bookings have been deleted!');
   	 iwrite('Press enter to contiunue...');
    	 readln;
      end;
end;

begin

init;
seats['A',4].name := 'C Prompte';
seats['B',2].name := 'Mr J Bloggs';
seats['D',1].name := 'D Ohss';
seats['E',3].name := 'Miss K Bhoard';
seats['F',2].name := 'Whin Entee';
   repeat;
      pageTitle;
      mainmenu;
      indent:='   ';
         case menusel of
            'V','v': viewBooking;
            'M','m': makeBooking;
            'E','e': editBooking;
            'D','d': deleteBooking;
            'A','a': deleteAllBookings;
         end;
   until (menusel='x') or (menusel='X');

exit:
pageTitle;
writeln;
writeln;
writeln;
indent:='                       ';
iwriteln('This program will now end!');
writeln;
iwriteln('Thankyou for using');
iwriteln('Airline Booking Systems.');
writeln;
iwrite('Please press any key to continue.');
readln;
end.
