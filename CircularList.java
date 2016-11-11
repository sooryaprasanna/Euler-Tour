/** Circular List Implementation
 *  @author Soorya Prasanna Ravichandran
 */
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Implementation of CircularList to save the sub-tours and the final tour
public class CircularList<T> implements Iterable<T>
{
	class ListNode
	{
		public T data;
		public ListNode next;

		public ListNode(T node)
		{
			this.data = node;
			this.next = null;
		}
	}

	ListNode tail;
	ListNode head;

	public CircularList()
	{
		tail = null;
	}

	public void addNode(T data)
	{
		if(tail == null)
		{
			head = new ListNode(data);
			tail = head;
			tail.next = head;
		}
		else
		{
			tail.next = new ListNode(data);
			tail = tail.next;
			tail.next = head;
		}
	}

	public boolean isEmpty()
	{
		if(head == null)
		{
			return true;
		}

		return false;
	}

	@Override
	public Iterator<T> iterator() {

		if(isEmpty())
		{
			return Collections.<T>emptyList().iterator();
		}

		return new Iterator<T>()
		{
			//for iterator;
			ListNode currNode = head;
			boolean firstTime = true;

			@Override
			public boolean hasNext()
			{
				if(firstTime && head == tail)
				{
					firstTime = false;
					return true;
				}
				else if(!firstTime && head == tail)
				{
					return false;
				}

				if(firstTime)
				{
					firstTime = false;
					return currNode.next != head;
				}
				else
				{
					return currNode != head;
				}
			}

			@Override
			public T next()
			{
				if(currNode == null)
				{
					throw new NoSuchElementException();
				}

				T d = currNode.data;

				currNode = currNode.next;

				return d;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}
	
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		Iterator<T> it = this.iterator();
		while(it.hasNext())
		{
			str.append(it.next());
			str.append("\n");
		}
		return str.toString();
	}
}
