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
{ Current Version: 1.0        }
{ Started November 2002       }
{ Not yet complete            }
{                             }
{ Improvements since 0.9:     }
{ > Time procedure added      }
{ > gotoXY function used on   }
{   pageTitle                 }
{ > delaydots procedure added }
{                             }
{ =========================== }

uses
        dos, crt, strings, vpSysLow;

var
        ok : boolean; // if ok=false, program will terminate
        menuOption : char;
        accountTotal, withdraw : real;

{ ============================= }
{ pageTitle - for uniform title }
{ ============================= }
procedure pageTitle;
var
        x, y : integer;
const
        titleblank = (' ========================== '); // goes at top and bottom of title text
begin
        x:=27; // space away from side
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
                i:=random(2000); // random number upto 2000ms (2 seconds)
                count:=count+1; // each time 1 is added on
                delay(i);
                write('.');
                end;
        until (count=dots); // repeat untill count = dots
end;

{ ================================ }
{ time - shows current system time }
{ ================================ }
procedure time;

var
        Year, Month, Day, DayOfWeek, Hour, Minute, Second, Sec100: LongInt;
        x, y : integer;
const
        blankBg = '                                                   ';
begin
        x:=15; // space away from side
        y:=7; // space away from top

        GetDate(Year, Month, Day, DayOfWeek);
        GetTime(Hour, Minute, Second, Sec100);

        gotoxy(x,y); // starting point
        textbackground(blue);
        write(blankBg);
        writeln;
        textbackground(black);
        gotoxy(x,y+1); // move down 1
        textbackground(blue);
        write(' The current time is ');

        write(Hour: 2, ':', Minute:2, ' on ');
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
        textbackground(black);
        gotoxy(x,y+2); // move down 1
        textbackground(blue);
        write(blankBg);
        writeln;
        textbackground(black);
        writeln;

end;

{ ========================================== }
{ enterinfo - gathers a valid account number }
{ ========================================== }
procedure enterinfo;
var
        accountNum : real;
        yn : string;
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
        yn : string;
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

{ ==================== }
{ PROGRAM BEGINS HERE! }
{ ==================== }
begin
      pageTitle;
      writeln;
      time;
      writeln;
      enterinfo;
        if ok then // if user has entered a valid account number
                begin;
        enterpin; // they can then continue to enter a PIN
        if ok then // if user has entered a valid PIN
                begin;
                        randomize; // enable random()
                        accountTotal:=random(10000); // generate a balence of upto $10000
                        write(' Please wait while we connect you');
                        delaydots(5);
                        write(' You are now connected!');
                        delay(2000); // delay for 2 seconds so user can read above text
                        repeat;
                                pageTitle;
                                writeln;
                                time;
                                writeln;
                                writeln(' What would you like to do?');
                                writeln;
                                writeln(' (V)iew Balance');
                                writeln(' (W)ithdraw Cash');
                                writeln(' E(x)it');
                                writeln;
                                write(' ');
                                readln(menuOption);
                                writeln;
                                case menuOption of
                                'V','v': // if (V)iew Balance was selected
                                                begin
                                                writeln(' You have $',accountTotal:0:2,' in your account');
                                                writeln;
                                                write(' Press any key to continue...');
                                                readln;
                                                end;
                                'W','w': // (W)ithdraw Cash was selected
                                                begin
                                                writeln(' How much would you like to withdraw?');
                                                write(' $');
                                                readln(withdraw);
                                                writeln;
                                                write(' Please wait, your transaction is being processed');
                                                delaydots(3);
                                                writeln;
                                                writeln;
                                                if(withdraw<accountTotal) or (withdraw=accountTotal) then
                                                        begin;
                                                        writeln(' $',withdraw:0:2,' has been deducted from your total funds ($',accountTotal:0:2,');');
                                                        accountTotal:=accountTotal-withdraw;
                                                        writeln(' You now have $',accountTotal:0:2,' in your account.');
                                                        writeln(' Please remove your cash from the machine now!');
                                                        end
                                                else
                                                        begin;
                                                        writeln(' You can not withdraw more than $',accountTotal:0:2,' (your current funds).');
                                                        end;

                                                writeln;
                                                write(' Press any key to continue...');
                                                readln;
                                                end;
                                end;
                                until (menuOption='X') or (menuOption='x'); // untill user chooses E(x)it

                        write(' Goodbye!');
                        delay(2000);
                        ok:=false;
                        end; //end ok for pin

                end; //end ok for account num

if (ok=false) then // if ok=false, end the program
end.
