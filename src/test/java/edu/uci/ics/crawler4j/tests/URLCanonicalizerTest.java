package edu.uci.ics.crawler4j.tests;

import junit.framework.TestCase;
import edu.uci.ics.crawler4j.url.URLCanonicalizer;

public class URLCanonicalizerTest extends TestCase {

	public static void testCanonizalier() {

		assertEquals("http://www.example.com/display?category=foo/bar+baz",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/display?category=foo/bar+baz"));

		assertEquals("http://www.example.com/?q=a+b",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/?q=a+b"));

		assertEquals("http://www.example.com/display?category=foo%2Fbar%2Bbaz",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/display?category=foo%2Fbar%2Bbaz"));

		assertEquals("http://somedomain.com/uploads/1/0/2/5/10259653/6199347.jpg?1325154037",
				URLCanonicalizer
						.getCanonicalURL("http://somedomain.com/uploads/1/0/2/5/10259653/6199347.jpg?1325154037"));

		assertEquals("http://hostname.com/", URLCanonicalizer.getCanonicalURL("http://hostname.com"));

		assertEquals("http://hostname.com/", URLCanonicalizer.getCanonicalURL("http://HOSTNAME.com"));

		assertEquals("http://www.example.com/index.html",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/index.html?&"));

		assertEquals("http://www.example.com/index.html",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/index.html?"));

		assertEquals("http://www.example.com/", URLCanonicalizer.getCanonicalURL("http://www.example.com"));

		assertEquals("http://www.example.com/bar.html",
				URLCanonicalizer.getCanonicalURL("http://www.example.com:80/bar.html"));

		assertEquals("http://www.example.com/index.html?name=test&rame=base",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/index.html?name=test&rame=base#123"));

		assertEquals("http://www.example.com/~username/",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/%7Eusername/"));

		assertEquals("http://www.example.com/A/B/index.html",
				URLCanonicalizer.getCanonicalURL("http://www.example.com//A//B/index.html"));

		assertEquals("http://www.example.com/index.html?x=y",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/index.html?&x=y"));

		assertEquals("http://www.example.com/a.html",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/../../a.html"));

		assertEquals("http://www.example.com/a/c/d.html",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/../a/b/../c/./d.html"));

		assertEquals("http://foo.bar.com/?baz=1", URLCanonicalizer.getCanonicalURL("http://foo.bar.com?baz=1"));

		assertEquals("http://www.example.com/index.html?c=d&e=f&a=b",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/index.html?&c=d&e=f&a=b"));

		assertEquals("http://www.example.com/index.html?q=a b",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/index.html?q=a b"));

		assertEquals("http://www.example.com/search?width=100%&height=100%",
				URLCanonicalizer.getCanonicalURL("http://www.example.com/search?width=100%&height=100%"));

		assertEquals("http://foo.bar/mydir/myfile?page=2",
				URLCanonicalizer.getCanonicalURL("?page=2", "http://foo.bar/mydir/myfile"));

        assertEquals("http://www.lampsplus.com/products/s_%20/",
                URLCanonicalizer.getCanonicalURL("http://www.lampsplus.com/products/s_ /"));

        assertEquals("http://www.vitacost.com/productResults.aspx?N=1300986+2009046",
                URLCanonicalizer.getCanonicalURL("http://www.vitacost.com/productResults.aspx?N=1300986+2009046"));

		assertEquals("http://www.pier1.com/Bright-Chenille-Striped-Rug/2527614,default,pd.html",
			URLCanonicalizer.getCanonicalURL("/Bright-Chenille-Striped-Rug/2527614,default,pd.html", "http://www.pier1.com/Bright-Chenille-Striped-Rug/2527614,default,pd.html"));

        assertEquals("http://www.bedbathandbeyond.com/store/product/orthaheel-gemma-women-39-s-leopard-slippers/3241401?Keyword=Orthaheel%60",
                URLCanonicalizer.getEncodedCanonicalURL("http://www.bedbathandbeyond.com/store/product/orthaheel-gemma-women-39-s-leopard-slippers/3241401?Keyword=Orthaheel`"));

        assertEquals("http://www.wrapables.com/categories.php?category=Gift-Ideas%2FBy-Occasion%2FWedding%7B47%7DShower",
                URLCanonicalizer.getEncodedCanonicalURL("http://www.wrapables.com/categories.php?category=Gift-Ideas/By-Occasion/Wedding{47}Shower"));

        assertEquals("http://www.zephyrsports.com/product/EF-2445-2279067/Elite-Force-M4M16-Universal-140rd-Mid-Cap-Airsoft-Magazines---10-Pack---Dark-Earth.html?catalog=HOMEPAGE&keywords=dye%5C&x=0&y=0",
                URLCanonicalizer.getEncodedCanonicalURL("http://www.zephyrsports.com/product/EF-2445-2279067/Elite-Force-M4M16-Universal-140rd-Mid-Cap-Airsoft-Magazines---10-Pack---Dark-Earth.html?catalog=HOMEPAGE&keywords=dye\\&x=0&y=0"));

        assertEquals("http://www.officesupply.com/office-supplies/general-supplies/indexing-flags-tabs/c200363.html?p=2&a3311113995=Pink&a3311114111=3%2B1%2F2%22%22",
                URLCanonicalizer.getEncodedCanonicalURL("http://www.officesupply.com/office-supplies/general-supplies/indexing-flags-tabs/c200363.html?p=2&a3311113995=Pink&a3311114111=3+1/2%22\""));


    }
}
