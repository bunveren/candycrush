CANDY CRUSH ÖDEVİ AÇIKLAMALARIM
Öğrenci Adı: Beren Ünveren
Öğrenci Numarası: 221101006

-Genel Aciklamalar ve Bonus Uygulamalari-
Genel Aciklamalar:
        -Fazladan çıktılar ekledim valid kontrolünden geçmesi veya match üstüne match yaşanması durumlarında
        çıktı veriyor ve ödevin doğru çalıştığı daha iyi gözlenebiliyor.

Implementasyona bagli durumlarda secimler:
        -Uc tane ayni tasin dikey veya yatay olarak yan yana gelmesini match kabul ettim.
        Baska bir match durumum yok.
        -1. modda her match 10 puan, 2. modda her match tum renkler icin ayni ve 5 puan.


Bonuslar:
        Upper cell'ler aşagi kaymiyor.
        Verilen colors sinifini kullandim ve renkli output bastiriyorum.
        Shuffle'da olan elemanlarin yeri degisecek sekilde yaptim
        (Renklerin bulunma miktarini tutup randomize bir sekilde yeniden dolduruyorum).


CALLFORSHUFFLE ACIKLAMA:
        fikir her bir tas icin cevresindeki taslarin rengine bakip oynayınca match olcak sekilde dizilmislerse
        bir oynayis bile legalse looptan cıkıp shuffle yaptirmamak
        eger hicbir sekilde oynanamiyorsa da shuffle cagirmak.
        ama oynanisi gerceklestirmek programi cok yoracagindan her hucre icin dort harekete bakip hareketi
        yapinca match oluyor mu diye bakmayi yapamadim civar hucreler icin renklere baktim 16 durum vardi 16n^2 oldu karmasiklik

EKSIKLER & FARKLILIKLAR:
        *belli bir eksik yok ama en son testleri yaparken nadiren en son matchi gormedigini fark ettigim icin
        iki kere clearboard cagirdim double check etmis oldu iki kere bastiriyor tahtanin en son durumunu
        *orneginizden fazla cikti verdirdim ki takibi kolay olsun diye
        *metotlarinizin cogunu birebir almadim cunku sadece onermissiniz zorunlu tutmamissiniz
        *metot signaturelari ve commentler:
            -void main(String[])
                //play_game metodunu cagirmayla basliyor ve play_game icinde input aliyor. s girilmezse oyundan cikiyor.
            -void play_game(Scanner)
            /*
                modu randomdan int alarak karar verdiriyorum.
                initialize boardi cagirip match olusmayacak sekilde boardi olusturuyorum.
                her adimda input metoduyla input aliyorum (input metodu icinde inputu alip bu hareket gecerli mi diye bakan
                isvalidi cagiriyor, sonra da isvalid metodu bu hareket match yaratiyor mu diye
                bakan ismakingsense i cagiriyor. eger validse makemove cagriliyor ve move sayisi azaltiliyor)
                sonrasinda shuffle icin kontrol edici metodu cagiriyorum.
                oyun bitisini de input alarak karar veriyorum ve booleani inputa gore bagliyorum.
                 */
            -void initializeBoard(Scanner)
            /*
                for loop icinde her eleman koyusta cevredeki elemanlara bakip bu elemanlarla match olusturuyor mu bakiyorum eger belirli renkte
                olusturuyorsa onu gecici olarak colors arrayinden cikarip onun secilememesini sagliyorum.
                 */
            -void take_input(Scanner)
            /*
                inputu alip bu hareket gecerli mi diye bakan isvalidi cagiriyor, sonra da isvalid metodu bu hareket match yaratiyor mu diye
                bakan ismakingsense i cagiriyor. eger validse makemove cagriliyor ve move sayisi azaltiliyor.
                 */
            -boolean isValidMove(int,int,String)
            /*
                burada invalid olacak tum durumlari dusunup onlari olcmeye calistim. en sonda da
                ismakingsense metodunu cagiriyorum cunku move legal olsa da match yaratmasi da gerekiyor
                 */
            -boolean isMakingSense(int,int,String)
            /*
                verilen konum ve hareketi temp boardda gerceklestirip match yaratiyor mu diye kontrol ediyorum
                 */
            -void makeMove(int,int,String)
            /*
                burada inputa gore bir yandaki tasla degisim hareketini gerceklestirip,
                boardi basip, en son matchleri silecek olan clearboard metodunu cagiriyorum.
                normalde bir kere cagirinca oluyordu bir anda sikinti cikti ben de clearboardi double check icin iki kere
                cagirdim bu yuzden cogu zaman en son olusan boardi iki kere basiyor sanirim
                 */
            -void clearBoard()
            /*
                buradaki booleanlarin amaci forloop icinde bir match gordugunde onu gerceklestirmesi,
                ikinci matchi gorurse basa donup ilk matchmis gibi degerlendirmesi ve ikinci matchi tekrar aramasi.
                yani en sonuncu iterasyonda ikinci matchi gormeyecek(baska match kalmamis olacak) ve bu sekilde looptan
                cikabilecek.
                skor vermeyi de burada ayarladim ve shuffle boarddan sonra clear yaparken puan eklenmesin diye boolean tuttum
                 */
            -boolean inBorder(int,int)
            /*
                shufflecall metodu icin yardimci kucuk bir metod
                 */
            -void callForShuffle()
            /*
                bunu her movedan sonra cagiriyorum ki hicbir match yapabilecek move olmadigini anladiginda shufflei cagirsin
                16 farkli durumu inceliyorum bir tane bile match durumu yaratilabiliyorsa looptan cikacak
                match olabilmesini de tas renklerini karsilastirarak kontrol ettim
                 */
            -void shuffleBoard()
            /*
                tas renklerinin sayisini tutuyor boardi bosaltiyor ve tekrar dolduruyor (sayiya gore ve randomize bir sekilde)
                 */
            -void printBoard()
            /*
                verilen colors classini kullandim ve string arrayi kullandigim icin gelen stringe gore color variableini
                basilacak stringe ekliyorum
                 */


            metot ustu commentleri buraya da kopyaladim

