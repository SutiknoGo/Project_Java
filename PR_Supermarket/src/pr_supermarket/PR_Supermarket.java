package pr_supermarket;

import java.util.Scanner;


public class PR_Supermarket {
	private String nama,_nama;
	private int harga, _harga;
	private int stok, _stok;

	public void print(){
		System.out.println(nama+ "       "+ harga+"        "+stok);
	}
	public PR_Supermarket(){}
	public PR_Supermarket(String _nama, int _harga, int _stok){
		nama = _nama;
		harga = _harga;
		stok = _stok;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}

	public void setStok(int stok) {
		this.stok = stok;
	}

	public String getNama() {
		return nama;
	}

	public int getHarga() {
		return harga;
	}

	public int getStok() {
		return stok;
	}
	public static void main(String[] args) {
		int chc =9,chc1=9, index=0, index1=0,index2, harga, beli, stok, total=0, bayar, kembalian, kurangi,stokskrg=0,hargaskrg=0;
		String nama;

		PR_Supermarket[] a = new PR_Supermarket[100];
		PR_Supermarket[] b = new PR_Supermarket[100];
		for(int i = 0; i < a.length; i++){
			a[i] = new PR_Supermarket();
			b[i] = new PR_Supermarket();
		}


		while (chc != 4){
			Scanner sc = new Scanner(System.in);
			System.out.print("Menu:\n 1. Add Barang\n 2. View Barang\n 3. Beli Barang\n 4. Exit\nChoice: ");
			chc = sc.nextInt();
			chc1=9;
			if (chc == 1){

				System.out.print( "Input Nama Barang: ");
				nama = sc.next();

				boolean found = false;

				for (int i = 0; i < index; i++){
					if(a[i].getNama().equals(nama)){
						System.out.println("Barang Sudah ada...");
						found = true;
						System.out.print("Input Jumlah Stok Yang Ditambah: ");
						stok = sc.nextInt();
						a[i].setStok(a[i].getStok() + stok);
					}
				}

				if (!found){
					a[index].setNama(nama);
					System.out.print( "Input Harga: ");
					harga = sc.nextInt();
					while (harga <= 0){
						System.out.print("Harga Harus Diatas 0...\n");
						System.out.print("Input Harga: ");
						harga = sc.nextInt();
					}
					if (harga > 0){
						a[index].setHarga(harga);
					}


					System.out.print("Input Stok: ");
					stok = sc.nextInt();
					while (stok <= 0){
						System.out.print("Stok Harus Diatas 0...\n");
						System.out.print("Input Stok: ");
						stok = sc.nextInt();
					}
					if( stok > 0){
						a[index].setStok(stok);
					}
					index++;
				}

			}


			else if (chc == 2){
				if (index == 0){
					System.out.print("Input Data Dulu...\n");
				}
				else {
					System.out.print("Nama" + "    " + "Harga" + "    " + "Stok\n"); 
					for (int i=0;i<index;i++){
						a[i].print();
					}
				}
			}

			else if (chc ==3){
				if (index != 0){
					while (chc1 != 4){
						System.out.print("Menu:\n 1. Add Barang yang dibeli\n 2. Delete Barang yang dibeli\n 3. Bayar\n 4. Kembali\nChoice: ");
						chc1 = sc.nextInt();
						boolean found = false;
						if (chc1 == 1){
							System.out.print("Nama" + "    " + "Harga" + "    " + "Stok\n"); 
							for (int i=0;i<index;i++){	
								a[i].print();
							}
							System.out.print("Input Nama Barang Yang Dibeli: "); 
							nama = sc.next();

							for (int i = 0; i < index1; i++){
								if(b[i].getNama().equals(nama)){
									System.out.println("Barang Sudah Ada...");
									found = true;
									System.out.print("Input Jumlah Stok Yang Ditambah: ");
									beli = sc.nextInt();
									b[i].setStok(b[i].getStok() + beli);

									if(a[i].getNama().equals(nama)){
										stokskrg = a[i].getStok();
										hargaskrg = a[i].getHarga();
										total += beli * hargaskrg;
										int sisa = stokskrg - beli;
										a[i].setStok(sisa);
									}
								}
							}                   
							if (!found){
								b[index1].setNama(nama); 
								System.out.print("Input Jumlah Barang: ");
								beli = sc.nextInt();
								for (int i =0;i<index;i++){
									if(a[i].getNama().equals(nama)){
										stokskrg = a[i].getStok();
										hargaskrg = a[i].getHarga();
									}
								}
								while (beli > stokskrg){
									System.out.print("Jumlah Melebihi Stok Yang Ada...\n");
									System.out.print("Input Jumlah Barang: ");
									beli = sc.nextInt();
								}
								if (beli <= stokskrg){
									b[index1].setHarga(hargaskrg);
									b[index1].setStok(beli);
									total += beli * hargaskrg;
									int sisa = stokskrg - beli;
									for (int i =0;i<index;i++){
										if(a[i].getNama().equals(nama)){
											a[i].setStok(sisa);
										}
									}
								}
								index1++;
							}
						}
						else if (chc1 == 2){
							if (index1 !=0){
								System.out.print("Nama" + "    " + "Harga" + "    " + "Stok\n"); 
								for (int i=0;i<index1;i++){
									b[i].print();
								}
								System.out.print("Input Index Barang Yang Dikurangi: ");
								index2 = sc.nextInt();
								nama = b[index2].getNama();
								while (index2 > index1-1 || index2 < 0){
									System.out.print("Isi Kembali Index Barang...\n");
									System.out.print("Input Index Barang Yang Dikurangi: ");
									index2 = sc.nextInt();
									nama = b[index2].getNama();
								}
								System.out.print("Input Berapa Banyak Yang Dikurangi: ");
								kurangi = sc.nextInt();
								for (int i=0;i<index;i++){
									if (a[i].getNama().equals(nama)){
										int kembali = b[index2].getStok() - kurangi;
										b[index2].setStok(kembali);
										a[index2].setStok(a[index2].getStok() + kurangi);
										total -= kurangi * b[index2].getHarga();
									}
								}

							}
							else{
								System.out.println("Input Barang Yang Di Beli Dulu ...");
							}
						}
						else if (chc1 == 3){
							System.out.print("Total Harga: " + total + "\n");
							System.out.print ("Dibayar: ");
							bayar = sc.nextInt();
							if (bayar < total){
								System.out.print("Uang Kurang...\n");
							}
							else{
								kembalian = bayar - total;
								System.out.print("Lunas, Kembalian: " + kembalian + "\n");
							}
						}
					}
				}
			}
			else {
				System.out.println("Input Data Dulu ...");
			}
		}
	}
}
