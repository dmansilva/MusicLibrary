package threadSafety;

import java.util.HashMap;

/**
 * A read/write lock that allows multiple readers, disallows multiple writers, and allows a writer to 
 * acquire a read lock while holding the write lock. 
 * 
 */
public class Lock {

	
	private HashMap<Long, Integer> numOfReaders;
	private HashMap<Long, Integer> numOfWriters;
	
	

	/**
	 * Construct a new ReentrantLock.
	 */
	public Lock() {
		
		this.numOfReaders = new HashMap<>();
		this.numOfWriters = new HashMap<>();
		
		
	}

	/**
	 * Returns true if the invoking thread holds a read lock.
	 * @return
	 */
	public synchronized boolean hasRead() {
		
		
		if (this.numOfReaders.containsKey(Thread.currentThread().getId())) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the invoking thread holds a write lock.
	 * @return
	 */
	public synchronized boolean hasWrite() {
		
		
		if (this.numOfWriters.containsKey(Thread.currentThread().getId())) {
			return true;
		}
		return false;
	}

	/**
	 * Non-blocking method that attempts to acquire the read lock.
	 * Returns true if successful.
	 * @return
	 */
	public synchronized boolean tryLockRead() {
		
		if ((this.numOfWriters.size() == 0) || (this.numOfWriters.size() >= 1 && this.numOfWriters.containsKey(Thread.currentThread().getId()))) {
				
			if (this.numOfReaders.get(Thread.currentThread().getId()) == null) {
				int value = 1;
				this.numOfReaders.put(Thread.currentThread().getId(), value);
			}
			else {
				int value = this.numOfReaders.get(Thread.currentThread().getId());
				value++;
				this.numOfReaders.put(Thread.currentThread().getId(), value);
			}
			return true;
		}
		
		return false;

	}

	/**
	 * Non-blocking method that attempts to acquire the write lock.
	 * Returns true if successful.
	 * @return
	 */	
	public synchronized boolean tryLockWrite() {

		if ((this.numOfReaders.size() == 0 && this.numOfWriters.size() == 0) || (this.numOfWriters.size() >= 1 && this.numOfWriters.containsKey(Thread.currentThread().getId()))) {
			
			if (this.numOfWriters.get(Thread.currentThread().getId()) == null) {
				int value = 1;
				this.numOfWriters.put(Thread.currentThread().getId(), value);
			}
			else {
				int value = this.numOfWriters.get(Thread.currentThread().getId());
				value++;
				this.numOfWriters.put(Thread.currentThread().getId(), value);
			}
			return true;
		}
		
		return false;

	}

	/**
	 * Blocking method that will return only when the read lock has been 
	 * acquired.
	 */	 
	public synchronized void lockRead() {
		
		
		
		while (!tryLockRead()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		
	}

	/**
	 * Releases the read lock held by the calling thread. Other threads may continue
	 * to hold a read lock.
	 */
	public synchronized void unlockRead() {
		
		
		if (hasRead()) {
			
			int value = this.numOfReaders.get(Thread.currentThread().getId());
			if (value > 1) {
				value--;
				this.numOfReaders.put(Thread.currentThread().getId(), value);
				
			}		
			
			else {
				this.numOfReaders.remove(Thread.currentThread().getId());
				
				notifyAll();
			}
				
			
		}
		
			
	}

	/**
	 * Blocking method that will return only when the write lock has been 
	 * acquired.
	 */
	public synchronized void lockWrite() {
		
		
		
		while (!tryLockWrite()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();			
			}
		}

	}

	/**
	 * Releases the write lock held by the calling thread. The calling thread may continue to hold
	 * a read lock.
	 */
	public synchronized void unlockWrite() {
		
		if (hasWrite()) {
			int value = this.numOfWriters.get(Thread.currentThread().getId());
			if (value > 1) {
				value--;
				this.numOfWriters.put(Thread.currentThread().getId(), value);
			}
			else {
				this.numOfWriters.remove(Thread.currentThread().getId());
				notifyAll();
			}
		}
		
		
	}
}
