package ohtu.ohtuvarasto;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void kayttoKelvotonVarasto() {
        Varasto huonoVarasto = new Varasto(-0.1);
        System.out.println(varasto.getTilavuus());
        assertEquals(0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoOlionKuormitus() {
        Varasto kuormitusVarasto = new Varasto(10, 5);

        assertEquals(5, kuormitusVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoOlionKuormitus2() {
        Varasto kuormitusVarasto = new Varasto(-1, 2);

        assertEquals(0, kuormitusVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoOlionKuormitus3() {
        Varasto kv = new Varasto(-1, -1);
        assertEquals(0, kv.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisays() {
        double vanha = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(vanha, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylivuotoTesti() {
        varasto.lisaaVarastoon(99999999);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOtto() {
        assertEquals(0.0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void kaikkiMitaVoidaan() {
        double kaikki = varasto.getSaldo();
        assertEquals(kaikki, varasto.otaVarastosta(999999), vertailuTarkkuus);
    }

    @Test
    public void toStringTesti() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void mainTest() {
        Main main = new Main();
        main.main(new String[2]);
        assertEquals(0,0);
    }
}