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
{ Current Version: 0.9        }
{ Started November 2002       }
{ Not yet complete            }
{                             }
{ Improvements since 0.8:     }
{ > Program totaly de-bugged  }
{                             }
{ =========================== }

uses
        crt;
var
        ok : boolean;
        menuOption : char;

 { ------------------------------------ }
 { PAGETITLE - Proceedure	          }
 { Shows the same title for each page   }
 { ------------------------------------ }
procedure pageTitle;
const
        titleblank = (' ========================== ');
        titlespace = ('                        ');
begin
        clrscr;
        writeln;
                textcolor(white);
        write(titlespace);
                textbackground(blue);
        write(titleblank);
        writeln;
                textbackground(black);
        write(titlespace);
                textbackground(blue);
        write(' Forward Thinking Bank Plc. ');
        writeln;
                textbackground(black);
        write(titlespace);
                textbackground(blue);
        write('        ATM Services        ');
        writeln;
                textbackground(black);
        write(titlespace);
                textbackground(blue);
        write(titleblank);
                textbackground(black);
                textcolor(white);
        writeln;
end;

 { ------------------------------------ }
 { ENTERINFO - Proceedure               }
 { Veriafies correct account number     }
 { ------------------------------------ }
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
                ok:=true;
                end
        else
                begin;
                write(' Account number is invalid, retry? (Y/N) ');
                readln(yn);
                if (yn='Y') or (yn='y') or (yn='yes') then
                        begin;
                        writeln;
                        enterinfo;
                        end
                else
                        begin;
                        writeln;
                        write(' Goodbye!');
                        delay(2000);
                        ok:=false;
                        exit;
                        end;
                end;
end;

 { ------------------------------------ }
 { ENTERPIN - Proceedure                }
 { Veriafies correct pin                }
 { ------------------------------------ }
procedure enterpin;
var
        pinNum : real;
        yn : string;
begin
        write(' Please enter your 4 or 6 digit PIN number: ');
        readln(pinNum);
        writeln;
        if (pinNum>1000) and (pinNum<9999) then
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
                        enterpin;
                        end
                else
                        begin;
                        writeln;
                        write(' Goodbye!');
                        delay(2000);
                        ok:=false;
                        exit;
                        end;
                end;
end;

const
        accountTotal = 124.14;
begin
	clrscr;
      pageTitle;
      writeln;
      writeln;
      enterinfo;
    	if ok then
    		begin;
       	enterpin;
       	if ok then
       		begin;
        		write(' Please wait while we connect you');
        		delay(700);
        		write('.');
        		delay(700);
        		write('.');
        		delay(700);
        		write('.');
        		delay(700);
        		write('.');
        		delay(700);
        		write('.');
        		delay(700);
        		write('.');
        		delay(700);
        		write(' You are now connected!');
        		delay(2000);
      			repeat
        			clrscr;
        			pageTitle;
        			writeln;
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
            			'V','v':
                				begin
                				write(' You have o',accountTotal:0:2,' In your account');
                				readln;
                				end;
            			'W','w':
                				begin
                				write(' How much would you like to withdraw?');
                				readln;
                				end;
        			end;
     				until (menuOption='X') or (menuOption='x');
			ok:=false;
   			end; {end ok for pin}

   		end; {end ok for account num}

if (ok=false) then
end.
