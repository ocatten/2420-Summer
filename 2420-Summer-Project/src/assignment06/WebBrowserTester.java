/**
 * Tester class for the WebBrowser
 * 
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 06:22:23 CS-2420_001 SUM-2023
 */
package assignment06;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;

import assignment06.SinglyLinkedList.Node;

public class WebBrowserTester {
	
/*--------------------------------------------------- SET UP ----------------------------------------------------------*/
	
	public WebBrowser createBrowserWithoutHistory() throws MalformedURLException {
		
		SinglyLinkedList<URL> history = new SinglyLinkedList<URL>();	
		 return new WebBrowser(history);
	}
	
	
	
	public WebBrowser createBrowserWithHistory() throws MalformedURLException {
		
		SinglyLinkedList<URL> history = new SinglyLinkedList<URL>();
		history.addNode(new URL("http://example.com/"));
		history.addNode(new URL("http://youtube.com/"));
		history.addNode(new URL("http://google.com/"));
		
		 return new WebBrowser(history);
	}
	
	
	
	public WebBrowser createBrowserWithLongHistory() throws MalformedURLException {
		
		SinglyLinkedList<URL> history = new SinglyLinkedList<URL>();
		
		for(int i = 0; i < 1000; i ++) {
			
			history.addNode( new URL("http://" + i + ".com/") );
		}
		
		return new WebBrowser(history);
	}
	
/*-------------------------------------------- CONSTRUCTOR TESTS -------------------------------------------------------*/
	
	@Test
	public void constructorOnEmptyTest() throws MalformedURLException {
		
		WebBrowser tester = createBrowserWithoutHistory();
	}

	
	
	@Test
	public void constructorOnSmallTest() throws MalformedURLException {
		
		WebBrowser wb = createBrowserWithHistory();
	}
	
	
	
	@Test
	public void constructorOnLargeTest() throws MalformedURLException {
		
		WebBrowser wb = createBrowserWithLongHistory();
	}
	
/*-------------------------------------------------VISIT TESTS--------------------------------------------------------*/
	
	@Test
	public void visitOnEmptyTest() throws MalformedURLException {
		
		WebBrowser wb = createBrowserWithoutHistory();
		wb.visit(new URL("http://google.com/"));
		
		LinkedListStack bckBttn = wb.getBack();
		
		assertEquals(1, bckBttn.size());
		assertEquals(bckBttn.peek().toString(), "http://google.com/");
		
	}
	
	
	
	@Test
	public void visitOnSmallTest() throws MalformedURLException {
		
		WebBrowser wb = createBrowserWithHistory();
		
		LinkedListStack bckBttn = wb.getBack();
		//System.out.println(bckBttn.peek());
		
		wb.visit(new URL("http://pizza.com/"));
		
		bckBttn = wb.getBack();
		//System.out.println(bckBttn.peek());
		
		assertEquals(3, bckBttn.size());
		assertEquals(bckBttn.peek().toString(), "http://pizza.com/");
		
	}
	
	
	
	@Test
	public void visitOnLargeTest() throws MalformedURLException {
		
		WebBrowser wb = createBrowserWithLongHistory();
		wb.visit(new URL("http://pizza.com/"));
		
		LinkedListStack bckBttn = wb.getBack();
		
		assertEquals(1000, bckBttn.size());
		assertEquals(bckBttn.peek().toString(), "http://pizza.com/");
		
	}
	
/*----------------------------------------------BACK TESTS-------------------------------------------------------------*/
	
	@Test
	public void backOnEmptyTest() throws MalformedURLException {
		
		WebBrowser wb = createBrowserWithoutHistory();
		
		try {
			
			wb.back();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(1==1);
		}
	}
	
	
	
	@Test
	public void backOnSmallTest() throws MalformedURLException {
		
		WebBrowser wb = createBrowserWithHistory();
		LinkedListStack bckBttn = wb.getBack();
		assertEquals(bckBttn.peek().toString(), "http://google.com/");
		
		wb.back();
		bckBttn = wb.getBack();
		
		assertEquals(bckBttn.peek().toString(), "http://youtube.com/");
		
		wb.back();
		bckBttn = wb.getBack();
		
		assertEquals("http://example.com/", bckBttn.peek().toString());
	}
	
/*------------------------------------------------------------*/	

}