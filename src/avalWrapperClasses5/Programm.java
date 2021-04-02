//Ex5
package avalWrapperClasses5;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Programm {

	public void run() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Insira IP e máscara: ");
		String im = sc.nextLine();
		String[] maskOctets = new String[4];
		String[] octets = new String[4];
		
		sc.close();
		
		String[] aux = new String[2];
		StringTokenizer tk = new StringTokenizer(im, "/");
		int i = 0;
		while (tk.hasMoreElements()) {
			aux[i] = tk.nextToken();
			i++;
		}
		String ip = aux[0];
		String mask = aux[1];
		octets = getIPs(ip);
		maskOctets = getMaskOctets(mask);
		int[] ipN = new int[octets.length];
		int[] maskN = new int[maskOctets.length];
		int[] subnetOctets = new int[octets.length];
		String subnetD = ".", subnetB = ".";

		for (i = 0; i < ipN.length; i++) {
			ipN[i] = Integer.valueOf(octets[i]);
			maskN[i] = Integer.valueOf(maskOctets[i], 2);
			subnetOctets[i] = ipN[i] & maskN[i];
			if (i == 0) {
				subnetD = Integer.toString(subnetOctets[i]);
				int bin = Integer.parseInt(Integer.toBinaryString(subnetOctets[i]));
				subnetB = String.format("%08d", bin);
				
			} else {
				subnetD += ".";
				subnetD += Integer.toString(subnetOctets[i]);
				int bin = Integer.parseInt(Integer.toBinaryString(subnetOctets[i]));
				subnetB += String.format(".%08d", bin);
			}
		}
		System.out.println("Rede em decimal: " + subnetD);
		System.out.println("Rede em binário: " + subnetB);

		
	}

	public static void main(String[] args) {
		new Programm().run();

	}

	public String[] getIPs(String ip) {
		String[] octetos = new String[4];

		StringTokenizer tk2 = new StringTokenizer(ip, ".");
		int i = 0;
		while (tk2.hasMoreElements()) {
			octetos[i] = tk2.nextToken();
			i++;
		}

		return octetos;
	}

	public String[] getMaskOctets(String mask) {
		String[] maskOctets = new String[4];
		int m = Integer.parseInt(mask);
		for (int j = 0; j < maskOctets.length; j++) {
			if (m > 0) {
				maskOctets[j] = "1";
				m--;
			} else {
				maskOctets[j] = "0";
			}
			for (int i = 1; i < 8; i++) {

				if (m > 0) {
					maskOctets[j] += "1";
					m--;
				} else {
					maskOctets[j] += "0";
				}
			}
		}

		return maskOctets;
	}
}