package lab05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

/**
 * This class demonstrates how to use iterators, for Lab 5.
 * 
 * @author CS 2420 course staff
 * @version June 6, 2023
 */
public class IteratorDemo {
	public static void main(String[] args) {
		SortedSet<Integer> ss = new TreeSet<>();
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(Integer i = 1; i < 6; i++) {
			array.add(i);
		}
		ss.addAll(array);
		
		var next = ss.iterator();
		System.out.println(next.toString());
		
		
		
		// How we see this
		for (int element : ss) {
			System.out.println(element);
		}

		// This is what it is really doing
		Iterator<Integer> iter = ss.iterator();
		while (iter.hasNext()) {
			int element = iter.next();
			System.out.println(element);
		}

		// Same thing, different loop
		for (Iterator<Integer> it = ss.iterator(); it.hasNext();) {
			int element = it.next();
			System.out.println(element);
		}

		// This works for ANY collection:
		Collection<Integer> collection = new ArrayList<>();
		collection = new LinkedList<Integer>();
		collection = new HashSet<>();
		collection = new Vector<>();
		collection = new PriorityQueue<>();

		Queue<Integer> q = new LinkedList<>();
		q = new PriorityQueue<>();

		Stack<Integer> stack = new Stack<>();

		Iterator<Integer> iterator;
		iterator = collection.iterator();
		iterator = q.iterator();
		iterator = stack.iterator();

		for (int whatever : collection) {
			System.out.println(whatever);
		}

		for (int something : q) {
			System.out.println(something);
		}

		for (int nothing : stack) {
			System.out.println(nothing);
		}
	}
}
