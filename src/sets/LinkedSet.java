package sets;

import java.util.Iterator;

public class LinkedSet<E> implements Set<E> {
	private LinkedNode<E> head = null;

	// Constructors
	public LinkedSet() {
	}

	public LinkedSet(E e) {
		this.head = new LinkedNode<E>(e, null);
	}

	private LinkedSet(LinkedNode<E> head) {
		this.head = head;
	}

	@Override
	public int size() {
		int count = 0;

		LinkedNodeIterator<E> temp = new LinkedNodeIterator<E>(head);
		while (temp.hasNext()) {
			count++;
			temp.next();
		}
		// TODO (1)
		return count;
	}

	@Override
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedNodeIterator<E>(head);
	}

	@Override
	public boolean contains(Object o) {
		boolean rval = false;
		LinkedNodeIterator<E> tempIt = (LinkedNodeIterator<E>) iterator();
		while (tempIt.hasNext()) {
			if (tempIt.next() == o) {
				rval = true;
			}
		}
		// TODO (3)
		return rval;
	}

	@Override
	public boolean isSubset(Set<E> that) {
		boolean rval = true;
		LinkedNodeIterator<E> tempThis = (LinkedNodeIterator<E>) iterator();
		// ? check if isEmpty?
		if (this.size() > that.size()) {
			rval = false;
		} else {
			while (tempThis.hasNext()) {
				E data = tempThis.next();
				LinkedNodeIterator<E> tempThat = (LinkedNodeIterator<E>) that.iterator();
				boolean tempCont = false;
				while (tempThat.hasNext()) {
					E tData = tempThat.next();
					if (data == tData) {
						tempCont = true;
						break;
					} else {
					}
				}
				if (tempCont == false) {
					rval = false;
					break;
				}
			}
		}
		return rval;
	}

	@Override
	public boolean isSuperset(Set<E> that) {
		boolean rval = that.isSubset(this);
		return rval;
	}

	@Override
	public Set<E> adjoin(E e) {

		if (contains(e) == true) {
			LinkedSet<E> rval = new LinkedSet<E>(head);
			return rval;
		} else {
			LinkedNode<E> temp = new LinkedNode<E>(e, head);
			LinkedSet<E> rval = new LinkedSet<E>(temp);
			return rval;
		}

	}

	@Override
	public Set<E> union(Set<E> that) {
		Set rval = new LinkedSet<E>(head);
		Iterator thisIt = this.iterator();
		Iterator thatIt = that.iterator();
		while (thisIt.hasNext()) {
			rval = rval.adjoin(thisIt.next());
		}
		while (thatIt.hasNext()) {
			rval = rval.adjoin(thatIt.next());
		}

		return rval;
	}

	@Override
	public Set<E> intersect(Set<E> that) {
		// TODO (8)
		Set rval = new LinkedSet<E>();
		Iterator<E> thisIt = this.iterator();
		Iterator<E> thatIt = that.iterator();
		if (this.isSubset(that)) {
			rval = this;
		} else if (that.isSubset(this)) {
			rval = that;
		} else if (that.size() > this.size()) {
			while (thatIt.hasNext()) {
				E obj = thatIt.next();
				if (this.contains(obj)) {
					rval = rval.adjoin(obj);
				}
			}
		}

		return rval;
	}

	@Override
	public Set<E> subtract(Set<E> that) {

		Set rval = new LinkedSet<E>();
		Iterator<E> rvalIt = iterator();
		if (this.isSubset(that) == false) {
			while (rvalIt.hasNext()) {
				E obj = rvalIt.next();
				if (that.contains(obj) == false) {
					rval = rval.adjoin(obj);
				}
			}
		}
		return rval;
	}

	@Override
	public Set<E> remove(E e) {
		Set rval = null;
		Iterator<E> thisIt = this.iterator();
		while (thisIt.hasNext()) {
			E data = (E) thisIt.next();
			if (data.equals(e) == false) {
				if (rval == null) {
					rval = new LinkedSet<E>(data);
				} else {
					rval = rval.adjoin(data);
				}
			}
		}
		// TODO (10)
		return rval;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof Set)) {
			return false;
		}
		Set<E> that = (Set<E>) o;
		return this.isSubset(that) && that.isSubset(this);
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (E e : this) {
			result += e.hashCode();
		}
		return result;
	}
}
