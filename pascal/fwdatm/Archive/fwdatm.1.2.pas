program fwdatm;

{ =========================== }
{                             }
{ Forward Thinking Bank Plc.  }
{ Virtual ATM Machine         }
{ 2002 Nick Bolton            }
{ Pascal Project              }
{                             }
{ fwdatm.exe simulates an     }
{ everyday ATM machine that   }
{ you may find on the         }
{ highstreet.                 }
{                             }
{ Current Version: 1.2        }
{ Started November 2002       }
{ Not yet complete            }
{                             }
{ Improvements since 1.1:     }
{ > time modified so that     }
{   leading zeros are added   }
{   for numbers uner 10       }
{ > withdraw feature now      }
{   gives you the option of   }
{   pre-defined amounts       }
{                             }
{ =========================== }

uses
        dos, crt, strings;

var
        ok : boolean; // if ok=false, program will terminate
        menuOption : char;
        intOption, other, accountTotal, withdraw : real;
        yn: string;
label
        menu;

{ ================================ }
{ time - shows current system time }
{ ================================ }
procedure time;

var
        Year, Month, Day, DayOfWeek, Hour, Minute, Second, Sec100: integer;
const
        blankBg = '                                                   ';
begin
        GetDate(Year, Month, Day, DayOfWeek);
        GetTime(Hour, Minute, Second, Sec100);

        gotoxy(13,7);
        write(' The current time is ');

        if (Hour<10) then
                write('0',Hour:1, ':') // add leading zero
        else
                write(Hour:2, ':');

        if (Minute<10) then
                write('0',Minute:1,' on ') // add leading zero
        else
                write(Minute:2,' on ');

        case DayOfWeek of
                0: write('Sunday');
                1: write('Monday');
                2: write('Tuesday');
                3: write('Wednesday');
                4: write('Thursday');
                5: write('Friday');
                6: write('Saturday');
        end;

        write(', ', Day:2, '/', Month:2, '/', Year:4, ' ');
        writeln;
end;


{ ============================= }
{ pageTitle - for uniform title }
{ ============================= }
procedure pageTitle;
var
        x, y : integer;
const
        titleblank = (' ========================== '); // goes at top and bottom of title text
begin
        x:=25; // space away from side
        y:=2; // space away from top

        clrscr; // clears screen every time title is shown
        writeln;
                textcolor(white);
        gotoxy(x,y); // starting point
                textbackground(blue);
        write(titleblank);
        writeln;
                textbackground(black);
        gotoxy(x,y+1); // move down 1 row
                textbackground(blue);
        write(' Forward Thinking Bank Plc. ');
        writeln;
                textbackground(black);
        gotoxy(x,y+2); // move down 1 row
                textbackground(blue);
        write('    Virtual ATM Machine     ');
        writeln;
                textbackground(black);
        gotoxy(x,y+3); // move down 1 row
                textbackground(blue);
        write(titleblank);
                textbackground(black);
                textcolor(white);
        writeln;
        writeln;
        time;
        writeln;
end;

{ ==================================================== }
{ delaydots - simulates a "connecting to" circumstance }
{ ==================================================== }
procedure delaydots(dots:integer); // gets "dots" as an integer
var
        count, i : integer;
begin
        count:=0; // start count at 0
        repeat
                begin;
                randomize; // enable random()
                count:=count+1; // each time 1 is added on
                delay(random(2000)); // random number upto 2000ms (2 seconds)
                write('.');
                end;
        until (count=dots); // repeat untill count = dots
end;

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

                delay(random(randMax)); // random number upto randMax
                gotoxy(x,y);
                write('-');
                delay(random(randMax));
                gotoxy(x,y);
                write('\');
                delay(random(randMax));
                gotoxy(x,y);
                write('|');
                delay(random(randMax));
                gotoxy(x,y);
                write('/');
                delay(random(randMax));

                end;
        until (count=rotations); // repeat untill count = dots
end;

{ ========================================== }
{ enterinfo - gathers a valid account number }
{ ========================================== }
procedure enterinfo;
var
        accountNum : real;
begin
        write(' Please enter your 8 digit account number: ');
        readln(accountNum);
        writeln;
        if (accountNum>15151515) and (accountNum<99999999) then
                begin;
                writeln(' Your account number is valid!');
                writeln;
                ok:=true; // if ok=true, program will not terminate
                end
        else
                begin;
                write(' Account number is invalid, retry? (Y/N) ');
                readln(yn);
                if (yn='Y') or (yn='y') or (yn='yes') then
                        begin;
                        writeln;
                        enterinfo; // go back to start of procedure
                        end
                else // terminates program
                        begin;
                        writeln;
                        write(' Goodbye!');
                        delay(2000); // delay by 2 seconds
                        ok:=false; // if ok=false, program will terminate
                        exit; // exit proceedure (and abort repeat)
                        end;
                end;
end;

{ ============================== }
{ enterpin - gathers a valid PIN }
{ ============================== }
procedure enterpin;
var
        pinNum : real;
begin
        write(' Please enter your 4 or 6 digit PIN number: ');
        readln(pinNum);
        writeln;
        if ((pinNum>1000) and (pinNum<9999)) or ((pinNum>99999) and (pinNum<999999))  then
        // determains a pin number with 4 or 6 digits
                begin;
                writeln(' Your PIN is valid!');
                writeln;
                ok:=true;
                end
        else
                begin;
                write(' PIN is invalid, retry? (Y/N) ');
                readln(yn);
                if (yn='Y') or (yn='y') or (yn='yes') then
                        begin;
                        writeln;
                        enterpin; // go back to start of procedure
                        end
                else // terminates program
                        begin;
                        writeln;
                        write(' Goodbye!');
                        delay(2000); // delay by 2 seconds
                        ok:=false; // if ok=false, program will terminate
                        exit; // exit proceedure (and abort repeat)
                        end;
                end;
end;

procedure viewBalence;
begin
        writeln(' You have $',accountTotal:0:2,' in your account');
        writeln;
        write(' Press enter to continue...');
        readln;
end;

{ ==================== }
{ PROGRAM BEGINS HERE! }
{ ==================== }
begin
      pageTitle;
      enterinfo;
        if ok then // if user has entered a valid account number
                begin;
        enterpin; // they can then continue to enter a PIN
        if ok then // if user has entered a valid PIN
                begin;
                        randomize; // enable random()
                        accountTotal:=random(1000); // generate a balence of upto $1000
                        write(' Please wait while we connect you');
                        delaydots(5);
                        //delayRotate(2,500,2,2);
                        write(' You are now connected!');
                        delay(2000); // delay for 2 seconds so user can read above text
                        pageTitle;
                        write(' Would you like to view your balence before you proceed? (Y/N) ');
                        readln(yn);
                        if(yn='Y') or (yn='y') or (yn='yes') then
                                begin
                                writeln;
                                viewBalence;
                                end;

                        menu:
                        repeat;
                                pageTitle;
                                writeln(' What would you like to do?');
                                writeln;
                                writeln(' (V)iew Balance');
                                writeln(' (W)ithdraw Cash');
                                writeln(' E(x)it');
                                writeln;
                                write(' Please choose a letter: ');
                                readln(menuOption);
                                writeln;
                                case menuOption of
                                'V','v': // if (V)iew Balance was selected
                                                begin
                                                pageTitle;
                                                viewBalence;
                                                end;
                                'W','w': // (W)ithdraw Cash was selected
                                                begin
                                                pageTitle;
                                                writeln(' How much would you like to withdraw?');
                                                writeln;
                                                writeln(' (1) $10');
                                                writeln(' (2) $20');
                                                writeln(' (3) $50');
                                                writeln(' (4) $100');
                                                writeln(' (5) $150');
                                                writeln(' (6) $200');
                                                writeln(' (7) Other');
                                                writeln(' (8) Cancel');
                                                writeln;
                                                write(' Please choose a number: ');
                                                readln(menuOption);

                                                if (menuOption='8') or (menuOption='x') or (menuOption='X') then
                                                        goto menu;
                                                if menuOption='7' then
                                                        begin
                                                        writeln;
                                                        writeln(' Please enter a custom amount;');
                                                        write(' $');
                                                        readln(other);
                                                        end;
                                                writeln;
                                                write(' Please wait, your transaction is being processed');
                                                delaydots(3);
                                                writeln;
                                                writeln;

                                                case menuOption of
                                                '1': withdraw:=10;
                                                '2': withdraw:=20;
                                                '3': withdraw:=50;
                                                '4': withdraw:=100;
                                                '5': withdraw:=150;
                                                '6': withdraw:=200;
                                                '7': withdraw:=other;
                                                end;

                                                if(withdraw<accountTotal) or (withdraw=accountTotal) then
                                                        begin;
                                                                writeln(' $',withdraw:0:2,' has been deducted from your total funds ($',accountTotal:0:2,');');
                                                                accountTotal:=accountTotal-withdraw;
                                                                writeln(' You now have $',accountTotal:0:2,' in your account.');
                                                                writeln(' Please remove your cash from the machine now!');
                                                                writeln;
                                                                write(' Press enter to continue...');
                                                                readln;
                                                        end

                                                else
                                                        begin
                                                                writeln(' You can not withdraw more than $',accountTotal:0:2,' (your current funds).');
                                                                writeln;
                                                                write(' Press enter to continue...');
                                                                readln;
                                                        end;
                                                end; // case statement
                                end; // case menuOption
                                until (menuOption='X') or (menuOption='x'); // untill user chooses E(x)it

                        write(' Goodbye!');
                        delay(2000);
                        ok:=false;
                        end; //end ok for pin

                end; //end ok for account num

if (ok=false) then // if ok=false, end the program
end.
