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
{ Current Version: 1.4        }
{ Started November 2002       }
{ Not yet complete            }
{                             }
{ Improvements since 1.3:     }
{ > Made maximum and minimum  }
{   available amount to       }
{   withdraw                  }
{ > Added total amout         }
{   withdrawn today feature   }
{ > Added 'withdraw maximum   }
{   feature' to some case     }
{   statements                }
{ > Modified exit message     }
{   method                    }
{                             }
{ =========================== }

uses
        dos, crt, strings;

var
        ok                                              : boolean;
        yn, menuOption                                  : char;
        intOption, other, accountTotal, withdraw,
        totalWithdrawn, todayMaxWithdraw                : real;
const
        maxWithdraw:real = 300; // maximum amount user can withdraw
        minWithdraw:real = 10; // minimum amount user can withdraw
label
        withdrawAmount, withdrawMenu, menu; // main menu

{ ================================ }
{ time - shows current system time }
{ ================================ }
procedure time;

var
        Year, Month, Day, DayOfWeek, Hour, Minute, Second, Sec100: longint;
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

        case DayOfWeek of // replace number with name
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
        time; // shows the time after the title
        writeln;
end;

{ ================================================= }
{ delaydots - simulates a "loading..." circumstance }
{ ================================================= }
procedure delaydots(randMax,dots:integer); // gets "dots" as an integer
var
        count, i : integer;
begin
        count:=0; // start count at 0
        repeat
                begin;
                randomize; // enable random()
                count:=count+1; // each time 1 is added on
                delay(random(randMax)); // random number upto 2000ms (2 seconds)
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

        gotoxy(x,y);
        write('All Done!');
        delay(1000);
        textbackground(black);

        pageTitle; // If screen isn't cleared, things get messy
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
                ok:=true; // if ok=true, program will not terminate
                end
        else
                begin;
                write(' Account number is invalid, retry? (Y/N) ');
                readln(yn);
                if (yn='Y') or (yn='y') or (yn='yes') then
                        begin;
                        //writeln;
                        pageTitle;
                        enterinfo; // go back to start of procedure
                        end
                else // terminates program
                        begin;
                        writeln;
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
        pageTitle;
        writeln(' Your account number is valid!');
        writeln;
        write(' Please enter your 4 or 6 digit PIN number: ');
        readln(pinNum);
        writeln;
        if ((pinNum>1000) and (pinNum<9999)) or ((pinNum>99999) and (pinNum<999999))  then
        // determains a pin number with 4 or 6 digits
                begin;
                pageTitle;
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
                        //writeln;
                        pageTitle;
                        enterpin; // go back to start of procedure
                        end
                else // terminates program
                        begin;
                        writeln;
                        ok:=false; // if ok=false, program will terminate
                        exit; // exit proceedure (and abort repeat)
                        end;
                end;
end;

procedure viewBalence;
begin
        writeln(' You have $',accountTotal:0:2,' in your account.');
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
                        write(' Please wait while we connect you...');
                        //delaydots(1000,4);
                        delayRotate(4,500,35,15);
                        //write('You are now connected!');
                        //delay(2000); // delay for 2 seconds so user can read above text
                        pageTitle;
                        write(' Would you like to view your balence on-screen before you proceed? (Y/N) ');
                        readln(yn);
                        if(yn='Y') or (yn='y') or (yn='yes') then
                                begin
                                        writeln;
                                        viewBalence; // used more than once
                                end;

                        menu:
                        repeat;
                                pageTitle;
                                writeln(' What would you like to do?');
                                writeln;
                                writeln(' (1) View Balance');
                                writeln(' (2) Withdraw Cash');
                                writeln(' (3) Print Mini-Statement');
                                writeln;
                                writeln(' (9) Exit'); // sets ok=flase, which will exit program
                                writeln;
                                write(' Please choose an option from above: ');
                                readln(menuOption);
                                writeln;
                                case menuOption of
                                '1': // if View Balance was selected
                                                begin
                                                        pageTitle;
                                                        viewBalence; // used more than once
                                                end;
                                '2': // Withdraw Cash was selected
                                                begin
                                                withdrawMenu:
                                                pageTitle;
                                                writeln(' How much would you like to withdraw?');
                                                writeln;
                                                writeln(' (1) $10    (5) $150');
                                                writeln(' (2) $20    (6) $200');
                                                writeln(' (3) $50    (7) Other');
                                                writeln(' (4) $100   (8) Cancel');
                                                writeln;
                                                write(' Please choose a number from 1 to 8: ');
                                                readln(menuOption);

                                                withdrawAmount: // label, so that maxWithdrawn feature can jump back here
                                                //readln(menuOption);
                                                if (menuOption='8')
                                                or (menuOption='C')
                                                or (menuOption='c')
                                                or (menuOption='X')
                                                or (menuOption='x') then // if "Cancel" is selected
                                                        goto menu;

                                                if menuOption='7' then
                                                        begin
                                                                writeln;
                                                                writeln(' Please enter a custom amount;');
                                                                write(' $');
                                                                readln(other);
                                                        end;
                                                writeln;
                                                write(' Please wait, your transaction is being processed...');

                                                delayRotate(2,200,34,13);
                                                //delaydots(500,3);
                                                //writeln;
                                                //writeln;

                                                case menuOption of
                                                '1': withdraw:=10;
                                                '2': withdraw:=20;
                                                '3': withdraw:=50;
                                                '4': withdraw:=100;
                                                '5': withdraw:=150;
                                                '6': withdraw:=200;
                                                '7': withdraw:=other; // other amount, defined erlier by user
                                                end;

                                                if(withdraw>0) then

                                                begin

                                                if((totalWithdrawn+withdraw)<(maxWithdraw+1)) then

                                                begin

                                                if(withdraw>maxWithdraw) then
                                                        begin
                                                                writeln(' You can not withdraw more than $',maxWithdraw:0:2,'.');
                                                                writeln;
                                                                write(' Would you like to withdraw $',maxWithdraw:0:2,'? (Y/N) ');
                                                                readln(yn);
                                                                if(yn='Y') or (yn='y') or (yn='yes') then
                                                                        begin
                                                                        withdraw:=maxWithdraw;
                                                                        menuOption:=' ';
                                                                        goto withdrawAmount;
                                                                        end
                                                                else
                                                                        goto withdrawMenu;
                                                        end;

                                                if(withdraw<minWithdraw) then
                                                        begin
                                                                writeln(' You can not withdraw less than $',minWithdraw:0:2,'.');
                                                                writeln;
                                                                write(' Would you like to withdraw $',minWithdraw:0:2,'? (Y/N) ');
                                                                readln(yn);
                                                                if(yn='Y') or (yn='y') or (yn='yes') then
                                                                        begin
                                                                        withdraw:=minWithdraw;
                                                                        menuOption:=' ';
                                                                        goto withdrawAmount;
                                                                        end
                                                                else
                                                                        goto withdrawMenu;

                                                        end;

                                                if(withdraw<accountTotal) or (withdraw=accountTotal) then
                                                        begin;
                                                                writeln(' $',withdraw:0:2,' has been deducted from your total funds ($',accountTotal:0:2,');');
                                                                totalWithdrawn:=totalWithdrawn+withdraw; // total amount user has withdrawn this session
                                                                accountTotal:=accountTotal-withdraw; // new account balence
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
                                                                write(' Would you like to withdraw $',accountTotal:0:2,'? (Y/N) ');
                                                                readln(yn);
                                                                if(yn='Y') or (yn='y') or (yn='yes') then
                                                                        begin
                                                                        withdraw:=accountTotal;
                                                                        menuOption:=' ';
                                                                        goto withdrawAmount;
                                                                        end
                                                                else
                                                                        goto withdrawMenu;
                                                        end;
                                                end

                                                else
                                                        begin
                                                                todayMaxWithdraw:=maxWithdraw-totalWithdrawn;
                                                                writeln(' $',withdraw:0:2,' could not be withdrawn from your account;');
                                                                writeln(' You can not withdraw more than $',maxWithdraw:0:2,' per day.');
                                                                writeln(' So far today, you have withdrawn $',totalWithdrawn:0:2,';');
                                                                writeln(' The maximum you can withdraw at this time, is $',todayMaxWithdraw:0:2);
                                                                writeln;
                                                                write(' Would you like to withdraw $',todayMaxWithdraw:0:2,'? (Y/N) ');
                                                                readln(yn);
                                                                if(yn='Y') or (yn='y') or (yn='yes') then
                                                                        begin
                                                                        withdraw:=todayMaxWithdraw;
                                                                        menuOption:=' ';
                                                                        goto withdrawAmount;
                                                                        end
                                                                else
                                                                        goto menu;
                                                        end;
                                                end

                                                else
                                                        begin
                                                        writeln(' Invalid amount specified!');
                                                        writeln;
                                                        write(' Press enter to continue...');
                                                        readln;
                                                        goto withdrawMenu;
                                                        end;
                                                end;

                                '3': // if Print Mini-Statement was selected
                                                begin
                                                pageTitle;
                                                writeln(' You have selected "Print Mini-Statement".');
                                                write(' Are you sure you want to do this? (Y/N) ');
                                                readln(yn);

                                                if(yn='Y') or (yn='y') or (yn='yes') then
                                                        begin
                                                        writeln;
                                                        write(' Printing Mini-Statement, Please wait...');
                                                        delay(5000);
                                                        writeln;
                                                        writeln;
                                                        writeln(' Your Mini-Statement has been successfully printed.');
                                                        write(' Please remove it from the machine now!');
                                                        writeln;
                                                        writeln;
                                                        write(' Press enter to continue...');
                                                        readln;
                                                        end
                                                else
                                                        goto menu;
                                                end;
                                end; // case menuOption
                                until (menuOption='X')
                                or (menuOption='x')
                                or (menuOption='C')
                                or (menuOption='c')
                                or (menuOption='9'); // untill user chooses E(x)it

                        ok:=false;
                        end; //end ok for pin

                end; //end ok for account num

        if (ok=false) then // if ok=false, end the program
                write(' Thankyou, Goodbye!'); // exit message
                delay(2000); // delay for 2 seconds so user can read exit message
                clrscr;
end.
