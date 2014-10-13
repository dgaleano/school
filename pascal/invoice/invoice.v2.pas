program invoice;

                   {--------------------------------------}
                   {           Invoice Creator            }
                   {        Written by Nick Bolton        }
                   {--------------------------------------}

                   {------------ Current todos -----------}
                   {                                      }
                   { find out how to input the same var   }
                   { more than once in order to generate  }
                   { a list of unlimited values           }
                   {                                      }
                   {--------------------------------------}

uses
        crt; {for clrscr}

type
        productinfo = record
                        name : char;
                        price, serial : real
                      end;

var
        product1                                : productinfo;
        {price, }item, subtotal, subvat, total  : real;
        yn, name                                : string;
        code                                    : string[4];
const
        vat = 0.175; {vat in œ}
        shipping = 5.00; {shipping in œ}
        help = (' Help: Enter 0 for price to exclude. Maximum 3 Items!'); {help on first page}
        itemspace =  ('                 '); {indents items}
        addmore = (' Would you like to add another item (Y/N)?');

        {invoice item stuff}
        inv_spacer = ('     '); {space between items}
        inv_name = (' *Name* '); {used for describing the name}
        inv_serial1 = (' ('); {left side of serial #}
        inv_serial2 = (') '); {right side of serial #}
        inv_price = ('*Price* œ'); {used for describing price}

        {Gather info stuff}
        gath_serial =   ('Item Serial code:        (4 Digit) #');
        gath_name =     ('Item Name:                          ');
        gath_price =    ('Item Price:                        œ');

{title same for each page}
procedure pageTitle;
const
        titleblank =    (' -=-=-=-=-=-=-=-=-=-=- '); {top and bottom of title}
        titlespace =    ('                           '); {centers title}
begin
        clrscr;
        writeln;
                textcolor(black);
        write(titlespace);
                textbackground(green);
        write(titleblank);
        writeln;
                textbackground(black);
        write(titlespace);
                textbackground(green);
        write(' -= Invoice Creator =- ');
        writeln;
                textbackground(black);
        write(titlespace);
                textbackground(green);
        write(titleblank);

                textbackground(black);
                textcolor(white);
        writeln;
end;

{for writing line at top and bottom of invoice}
procedure invsep;
begin
        write(' ------------------------------------------------------------------------------');        write ('');
        writeln;
end;

{gathers info from user}
procedure gatherinfo;
begin
        writeln;
        write (itemspace,gath_serial);
        readln(product1.serial);
        write (itemspace,gath_name);
        readln(product1.name);
        write (itemspace,gath_price);
        readln(product1.price);
        writeln;
        write (addmore);
        readln(yn);
        if (yn='Y') or (yn='y') or (yn='yes') then
        gatherinfo;
end;

begin
        pageTitle;
        writeln;
        writeln(help);
        writeln;

        gatherinfo;

        writeln;
        writeln(' Press enter now to create your invoice.');
        readln;

        {invoice page + title}
        pageTitle;
        writeln;
        invsep;
        {calc code}
        for product1.name := * do
                writeln(inv_name,product1.name,inv_serial1,product1.serial,inv_serial2,inv_spacer,inv_price,product1.price:0:2);
        {writeln(inv_name,name2,inv_serial1,code2,inv_serial2,inv_spacer,inv_price,price2:0:2);
        writeln(inv_name,name3,inv_serial1,code3,inv_serial2,inv_spacer,inv_price,price3:0:2);}
        subtotal:=product1.price{+price2+price3};
        writeln;
        writeln(' Subtotal:             œ',subtotal:0:2);
        writeln(' VAT:                  œ',vat:0:3);
        writeln(' Shipping:             œ',shipping:0:2);
        writeln;
        subvat:=subtotal*vat;
        total:=subtotal+subvat+shipping;
        writeln(' Total:                œ',total:0:2);
        invsep;
        writeln;
        write(' Press enter to end program.');
        readln;
end.
