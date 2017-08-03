import java.util.ArrayList;
import java.lang.Math;

class SChainingHash {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new Map<>(100);
		map.add("This", 0);
		map.add("is", 1);
		map.add("Sparta", 2);
		map.add("Yes", 3);
		System.out.println(map.get("This"));
		System.out.println(map.get("is"));
		System.out.println(map.get("Sparta"));
		System.out.println(map.get("No"));		
	}
}

class HashNode<K, V> {

	public K key;
	public V value;

	public HashNode<K, V> next;

	public HashNode(K key, V value) {

		this.key = key;
		this.value = value;
	}
}

class Map<K, V> {

	private ArrayList<HashNode<K, V>> chainArray;
	private int chainArrayCapacity;
	private int size;

	public Map(int chainArrayCapacity) {
		
		this.chainArrayCapacity = chainArrayCapacity;
		size = 0;
		chainArray = new ArrayList<>();

		for (int i = 0; i < chainArrayCapacity; i++) {
			chainArray.add(null);
		}
	}

	public int size() {return size;}

	public boolean isEmpty() {return size == 0;}

	private int getIndex(K key) {

		int hashCode = key.hashCode();
		int index = hashCode % chainArrayCapacity;
		return Math.abs(index);
	}

	public void add(K key, V value) {

		int index = getIndex(key);
		HashNode<K, V> link = chainArray.get(index);

		while (link != null) {

			if (link.key.equals(key)) {

				link.value = value;
				return;
			}
			link = link.next;
		}

		size++;
		link = chainArray.get(index);
		HashNode<K, V> newNode = new HashNode<K, V>(key, value);
		newNode.next = link;
		chainArray.set(index, newNode);

		if ((size/chainArrayCapacity) >= 0.7) {

			ArrayList<HashNode<K, V>> temp = chainArray;
			chainArray = new ArrayList<>();
			chainArrayCapacity *= 2;
			size = 0;
			for (int i = 0; i < chainArrayCapacity; i++) {chainArray.add(null);}

			for (HashNode<K, V> node: temp) {
				
				while (node != null) {

					add(node.key, node.value);
					node = node.next;
				}
			}
		}
	}

	public V get(K key) {

		int index = getIndex(key);
		HashNode<K, V> node = chainArray.get(index);

		while (node != null) {

			if (node.key.equals(key)) {return node.value;}
			node = node.next;
		}

		return null;
	}

	public V remove(K key) {

		int index = getIndex(key);
		HashNode<K, V> node = chainArray.get(index);
		HashNode<K, V> prev = null;		
		
		while (node != null) {

			if (node.key.equals(key)) {break;}
			prev = node;
			node = node.next;
		}

		if (node == null) {return null;}

		size--;
		if (prev != null) {prev.next = node.next;}
		else {chainArray.set(index, node.next);}
		return node.value;
	}
}