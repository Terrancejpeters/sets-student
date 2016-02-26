package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
	private LinkedNode<E> head;

	// Constructors
	public LinkedNodeIterator(LinkedNode<E> ihead) {
		head = ihead;
	}

	@Override
	public boolean hasNext() {
		boolean rval = false;
		if (head != null) {
			rval = true;

		} else {
			rval = false;
		}
		return rval;
		// TODO (3)
	}

	@Override
	public E next() {
		// TODO (4)
		E rVal = null;
		if (hasNext()) {
			rVal = head.getData();
			head = head.getNext();
		} else {
			throw new NoSuchElementException();
		}
		return rVal;

	}

	// Leave this one alone.
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
