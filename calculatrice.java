// IMPORTANT : Il faut créer un seul Scanner pour System.in dans tout le programme,
// car fermer un Scanner ferme aussi le flux System.in. 
// Si on crée plusieurs Scanner et qu'on en ferme un, 
// les autres Scanner ne pourront plus lire et cela provoquera des erreurs (NoSuchElementException).
// Donc, on crée un seul Scanner, qu’on partage et ferme uniquement à la fin du programme.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class calculatrice {

	public static double executercalculatrice(Scanner scanner) {
	    System.out.println("Calculatrice normale ");
	    System.out.println("Tapez 1 pour calculatrice normale.");
	    System.out.println("Tapez 2 pour pour calculer une expression numérique.");
	    
	    String m;
	    int mode = 0;

	    while (true) {
	        System.out.print("Taper 1 ou 2 : ");
	        m = scanner.nextLine().trim();

	        if (m.equals("1") || m.equals("2")) {
	            mode = Integer.parseInt(m);
	            break;
	        } else {
	            System.out.println("❌ Entrée invalide. Veuillez taper 1 ou 2.");
	        }
	    }

	    String expr = "";
	    List<Character> listes = new ArrayList<>();

	    if (mode == 2) {
	        System.out.print("Tapez votre expression (ex: 2 + 3 * 4) : ");
	        expr = scanner.nextLine();

	        while (true) {
	            boolean contientLettre = false;
	            for (int i = 0; i < expr.length(); i++) {
	                if (Character.isLetter(expr.charAt(i))) {
	                    contientLettre = true;
	                    break;
	                }
	            }
	            if (contientLettre) {
	                System.out.println("Expression incorrecte, recommencez : ");
	                System.out.print(">> ");
	                expr = scanner.nextLine();  
	            } else {
	                break; 
	            }
	        }

	        for (char c : expr.toCharArray()) {
	            if (c != ' ') {
	                listes.add(c);
	            }
	        }

	        while (true) {
	            boolean contientdoubleiperateur = false;
	            for (int i = 0; i < listes.size() - 1; i++) { 
	                char c1 = listes.get(i);
	                char c2 = listes.get(i + 1);
	                char c3 = listes.get(listes.size() - 1);

	                if (("+-*/".indexOf(c1) != -1 && "+-*/".indexOf(c2) != -1)||"+-*/".indexOf(c3) != -1) {
	                    contientdoubleiperateur = true;
	                    break;
	                }
	            }

	            if (contientdoubleiperateur) {
	                System.out.println("Expression incorrecte, recommencez : ");
	                System.out.print(">> ");
	                expr = scanner.nextLine();  
	                listes.clear();
	                for (char c : expr.toCharArray()) {
	                    if (c != ' ') {
	                        listes.add(c);
	                    }
	                }
	            } else {
	                break; 
	            }
	        }

	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < listes.size(); i++) {
	            char c = listes.get(i);
	            String d = c + "";

	            if ("+-/*".contains(d)) {
	                sb.append(" ").append(d).append(" ");
	            } else {
	                sb.append(c);
	            }
	        }

	        String expression = sb.toString();
	        double result = evaluerExpression(expression);
	        String expressionAvecPar = ajouterParentheses(expression);
	        expressionAvecPar = expressionAvecPar.substring(1, expressionAvecPar.length() - 1);
	        System.out.println("Résultat final de : " + expressionAvecPar + " = " + result);
	        return result;

	    } else {
	        boolean continuer = true;
	        double resultat = 0;
	        double nombre = 0;

	        while (continuer) {
	            String operateur = "";
	            boolean premier = true;
	            String reponse = "";
	            boolean deuxieme = true;
	            boolean troixieme = true;
	            String rp = "";

	            while (true) {
	                if (premier) {
	                    while (true) {
	                        System.out.print("Entrez un nombre : ");
	                        String input = scanner.nextLine().trim();

	                        try {
	                            if (!input.endsWith("d")) {
	                                resultat = Double.parseDouble(input);
	                                break;
	                            } else {
	                                System.out.println("Ce n'est pas un nombre valide. Réessayez.");
	                            }
	                        } catch (NumberFormatException e) {
	                            System.out.println("Ce n'est pas un nombre valide. Réessayez.");
	                        }
	                    }
	                    premier = false;
	                    deuxieme = true;
	                    troixieme = true;
	                }

	                if (troixieme) {
	                    System.out.print("Entrez un opérateur (+, -, *, /, =) : ");
	                    operateur = scanner.next();
	                    scanner.nextLine();
	                    String[] operateursValides = {"+", "-", "*", "/", "="};
	                    boolean valide = false;

	                    while (!valide) {
	                        for (String c : operateursValides) {
	                            if (operateur.equals(c)) {
	                                valide = true;
	                                break;
	                            }
	                        }
	                        if (!valide) {
	                            System.out.println("L'opérateur est incorrect.");
	                            System.out.print("Redonnez l'opérateur : ");
	                            operateur = scanner.next();
	                            scanner.nextLine();
	                        }
	                    }

	                    if (operateur.equals("=")) {
	                        System.out.println("Résultat final : " + resultat);
	                        System.out.println("voulez-vous répéter ou non ?");
	                        while (true) {
	                            rp = scanner.next();
	                            scanner.nextLine();
	                            if (rp.equals("oui")) {
	                                System.out.println("recommencer ; ");
	                                premier = true;
	                                deuxieme = false;
	                                break;
	                            } else if (rp.equals("non")) {
	                                System.out.println("merci au revoir .");
	                                continuer = false;
	                                break;
	                            } else {
	                                System.out.println("tu as tapé une fausse réponse.Refaire.");
	                            }
	                        }
	                        break;
	                    }
	                }

	                if (deuxieme) {
	                    while (true) {
	                        System.out.print("Entrez un autre nombre : ");
	                        String input = scanner.nextLine().trim();

	                        try {
	                            if (!input.endsWith("d")) {
	                                nombre = Double.parseDouble(input); // ✅ CORRIGÉ ICI
	                                break;
	                            } else {
	                                System.out.println("Ce n'est pas un nombre valide. Réessayez.");
	                            }
	                        } catch (NumberFormatException e) {
	                            System.out.println("Ce n'est pas un nombre valide. Réessayez.");
	                        }
	                    }
	                    troixieme = true;
	                }

	                switch (operateur) {
	                    case "+":
	                        resultat += nombre;
	                        break;
	                    case "-":
	                        resultat -= nombre;
	                        break;
	                    case "*":
	                        resultat *= nombre;
	                        break;
	                    case "/":
	                        if (nombre != 0) {
	                            resultat /= nombre;
	                        } else {
	                            System.out.println("Erreur : division par zéro !");
	                            while (true) {
	                                System.out.println("Voulez-vous changer le denominateur ou refaire opération ? taper d ou r ");
	                                System.out.print("taper : ");
	                                reponse = scanner.next();
	                                scanner.nextLine();
	                                if (reponse.equals("d")) {
	                                    deuxieme = true;
	                                    troixieme = false;
	                                    break;
	                                } else if (reponse.equals("r")) {
	                                    System.out.println("refaire l'opération.");
	                                    premier = true;
	                                    troixieme = true;
	                                    break;
	                                } else {
	                                    System.out.println("tu as tapé une fausse réponse.Refaire.");
	                                }
	                            }
	                        }
	                        break;
	                }
	            }
	        }
	        return resultat;
	    }
	}

        
    
    public static double evaluerExpression(String expr) {
        List<String> tokens = new ArrayList<>(Arrays.asList(expr.trim().split(" ")));

        // Étape 1 : gérer * et /
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("*") || tokens.get(i).equals("/")) {
                double a = Double.parseDouble(tokens.get(i - 1));
                double b = Double.parseDouble(tokens.get(i + 1));
                double res = tokens.get(i).equals("*") ? a * b : a / b;
                tokens.set(i - 1, String.valueOf(res));
                tokens.remove(i); // opérateur
                tokens.remove(i); // b
                i--;
            }
        }
        

        // Étape 2 : gérer + et -
        double result = Double.parseDouble(tokens.get(0));
        for (int i = 1; i < tokens.size(); i += 2) {
            String op = tokens.get(i);
            double b = Double.parseDouble(tokens.get(i + 1));
            if (op.equals("+")) result += b;
            else if (op.equals("-")) result -= b;
        }

        return result;
    }
    public static String ajouterParentheses(String expr) {
        List<String> tokens = new ArrayList<>(Arrays.asList(expr.trim().split(" ")));

        // Étape 1 : gérer * et /
        int i = 0;
        while (i < tokens.size()) {
            if (tokens.get(i).equals("*") || tokens.get(i).equals("/")) {
                String a = tokens.get(i - 1);
                String op = tokens.get(i);
                String b = tokens.get(i + 1);
                String avecPar = "(" + a + " " + op + " " + b + ")";
                tokens.set(i - 1, avecPar);
                tokens.remove(i); // opérateur
                tokens.remove(i); // b
                i--; // revenir en arrière pour continuer sur la liste modifiée
            } else {
                i++;
            }
        }

        // Étape 2 : gérer + et -
        while (tokens.size() > 1) {
            String a = tokens.get(0);
            String op = tokens.get(1);
            String b = tokens.get(2);
            String avecPar = "(" + a + " " + op + " " + b + ")";
            tokens.set(0, avecPar);
            tokens.remove(1);
            tokens.remove(1);
        }

        return tokens.get(0);
    }
    public static int pgcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static double convertirDegreEnRadianEtAfficher(double degre) {
        int precision = 100; // Plus grand = plus précis
        int num = (int) Math.round(degre * precision);
        int den = 180 * precision;

        int pgcd = pgcd(num, den);
        num /= pgcd;
        den /= pgcd;
        double radianvaleur = (num*Math.PI)/den;

        return radianvaleur;
    }

/*
 * partie calculatrice scientifique :
 */
    public static void calculatricescientifique(Scanner scanner) {
    	boolean continuer = true;
    	while (continuer) {
    	continuer = false;
        double puissance = 0.0;
        int n = 0;
        System.out.println("=== Calculatrice Scientifique ===");
        System.out.println("1. Puissance");
        System.out.println("2. Racine n-ème");
        System.out.println("3. radian vers degré ou inverse");
        System.out.println("4. cos");
        System.out.println("5. sin");
        System.out.println("6. tan");

        System.out.print("Choisissez une opération (1-6) : ");
        String choix = scanner.next();
        char rp = choix.charAt(0);
        String rep = rp + "";

        while (!"123456".contains(rep)) {
            System.out.println("Opération invalide. Réessayez.");
            System.out.print("Choisissez une opération (1-6) : ");
            choix = scanner.next();
            rp = choix.charAt(0);
            rep = rp + "";
        }
        if (rep.equals("1")) {
            System.out.print("donner la puissance :");
            n = scanner.nextInt();
            System.out.println("donner la valeur : ");
            System.out.println("choisir 1 (un nombre unique) ou 2 (une operation)");
            int nb = scanner.nextInt();
            while (true) {
                switch (nb) {
                    case 1:
                        System.out.println("choisir le nombre double de la forme ex ; 1.0 .");
                        double nbr = scanner.nextDouble();
                        puissance = Math.pow(nbr, n);
                        System.out.println("la puissance de " + nbr + " est " + puissance);
                        break;
                    case 2:
                        double finalResult = executercalculatrice(scanner);
                        puissance = Math.pow(finalResult, n);
                        System.out.println("la puissance de " + finalResult + " est " + puissance);
                        break;
                    default:
                        System.out.println("nombre incorrecte , rechoisir.");
                }
                break;
            }
        }
        if (rep.equals("2")) {
        	System.out.print("donner la puissance du n-ème :");
            n = scanner.nextInt();
            System.out.println("donner la valeur : ");
            System.out.print("choisir 1 (un nombre unique) ou 2 (une opération) : ");
            int nb = scanner.nextInt();
            while (true) {
                switch (nb) {
                    case 1:
                        System.out.print("choisir le nombre double de la forme ex ; 1.0 :");
                        double nbr = scanner.nextDouble();
                        puissance = Math.pow(nbr, 1.0/n);
                        // 1.0/n pas 1/n ça 1/n est partie entière
                        System.out.println("la racine "+n+"-ème de " + nbr + " est " + puissance);
                        break;
                    case 2:
                        double finalResult = executercalculatrice(scanner);
                        puissance = Math.pow(finalResult,1.0/n);
                        System.out.println("la racine "+n+"-ème de " + finalResult + " est " + puissance);
                        break;
                    default:
                        System.out.println("nombre incorrecte , rechoisir.");
                }
                break;
            }
        }
        if (rep.equals("3")) {
			System.out.print("choisir 1 (transformer en  degré) ou 2 (transformer en radian) : ");
		    int nb = scanner.nextInt();      
		    scanner.nextLine();
            while (true) {
                switch (nb) {
                case 1:
                    while (true) {
                    	double result;
                        System.out.print("Choisir un radian sous la forme par exemple; pi/4 ou un nombre décimal: ");
                        String nbr = scanner.nextLine().trim(); 
                        List<String> liste = new ArrayList<>();
                        if (nbr.contains("pi")) {
                            try {
                            	for (int j = 0; j < nbr.length(); j++) {
                                    char c = nbr.charAt(j);
                                    if (c != ' ') {
                                        if (c == 'p' && nbr.charAt(j + 1) == 'i') {
                                            liste.add("pi");
                                            j = j + 1; 
                                        } 
                                        else {
                                            liste.add(String.valueOf(c));
                                        }
                                    }
                                }
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < liste.size(); i++) {
                                		if (liste.get(i).equals("pi") ) {  
                                            sb.append(" pi");
                                        }else if (liste.get(i).equals("/")){
                                            sb.append(" / ");
                                        }else {
                                    		sb.append(liste.get(i));
                                        }
                                	
                                }
                                String expr = sb.toString();
                            
                             StringBuilder exprAvecMul = new StringBuilder();

                             for (int i = 0; i < expr.length(); i++) {
                                 char c = expr.charAt(i);
                               
                                 if (c == 'p') {
                                         exprAvecMul.append('*'); // ajoute '*'
                                     exprAvecMul.append(" pi");
                                     i = i + 1; // sauter le 'i' car déjà ajouté
                                 } else {
                                     exprAvecMul.append(c);
                                 }
                             }

                             String exprFinal = exprAvecMul.toString();
                             String exprAvec180 = exprFinal.replace("pi", "180");
                             StringBuilder exprAvecpo = new StringBuilder();
                             if (exprAvec180.charAt(0) == ' ' && exprAvec180.charAt(1) == '*' ) {
                            	 for (int i = 3; i < exprAvec180.length(); i++) {
                                     char c = exprAvec180.charAt(i);
                                     exprAvecpo.append(c);

                            	 }
                                 String exprFinaly = exprAvecpo.toString();
                                 result = evaluerExpression(exprFinaly);

                             }
                             else {
                                 result = evaluerExpression(exprAvec180);
                             }


                                System.out.println("Valeur en degrés : " + result + " °");
                                break;
                            } catch (Exception e) {
                                System.out.println("Erreur lors de l'évaluation. Vérifiez la syntaxe de votre expression.");
                            }
                        } 
                        else if(!nbr.contains("pi")){
                        	try {
                            	for (int j = 0; j < nbr.length(); j++) {
                                    char c = nbr.charAt(j);
                                    if (c != ' ') {
                                    	liste.add(String.valueOf(c));
               
                                    }
                                }
                            	
                            	StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < liste.size(); i++) {
                                		String c = liste.get(i);
                                		
                                		if ( "+-/*".contains(c)) {  
                                            sb.append(" "+c+" ");
                           
                                        }else {
                                    		sb.append(liste.get(i));
                                        }
                                	
                                }
                        	    String expression = sb.toString();
                        	    result = evaluerExpression(expression);
                            	
                                int den = 314;
                                int num = (int) Math.round(result * 100);
                                int pgcd = pgcd(num, den);
                                num /= pgcd;
                                den /= pgcd;
                                String exprAveccPi;
                                String exprAvecpi = "";
                                if (den == 1) {
                                	exprAveccPi = (num == 1 ? "pi" : num + " pi");
                                	exprAvecpi = (num == 1 ? "180" : num + " * 180");
                                } else if (num == 1) {
                                	exprAveccPi = "pi / " + den;
                                	exprAvecpi = "180 / " + den;
                                } else {
                                	exprAveccPi = num + " pi / " + den;
                                	exprAvecpi = num + " * 180 / " + den;
                                }

                                System.out.println("Valeur en radian : " + exprAveccPi );
                                
                                
                                double degres = evaluerExpression(exprAvecpi);
                                System.out.println("Valeur en degrés : " + degres + " °");
                                break;
                               
                                
                                
                            } catch (Exception e) {
                                System.out.println("Erreur lors de l'évaluation. Vérifiez la syntaxe de votre expression.");
                            }
                        }
                      
                    }
                    break;



                case 2:
                    double degreDouble = 0;
                    while (true) {
                        System.out.print("Entrez un angle en degrés : ");
                        String input = scanner.nextLine().trim();
                        try {
                            degreDouble = Double.parseDouble(input);  
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                        }
                    }
                    int precision = 100; // Plus grand = plus précis
                    int num = (int) Math.round(degreDouble * precision);
                    int den = 180 * precision;

                    int pgcd = pgcd(num, den);
                    num /= pgcd;
                    den /= pgcd;
                    String radian;
                    String rad = "";
                    if (den == 1) {
                        radian = (num == 1 ? "pi" : num + " pi");
                        rad = (num == 1 ? "pi" : num + " * pi");
                    } else if (num == 1) {
                        radian = "pi / " + den;
                    } else {
                        radian = num + " pi / " + den;
                        rad = num + " * pi / " + den;
                    }
                    String exprAvec180 = rad.replace("pi", "180");
                    double resultt = evaluerExpression(exprAvec180);
                    System.out.println("Valeur en radian : " + radian);
                    System.out.println("valeur en radian calculer : " + resultt + " rad/s" );
                    break;
                    default:
                        System.out.println("nombre incorrecte , rechoisir.");
                }
                break;
            }
        }
        if (rep.equals("4")) {
            double resultatCos = 0.0;
            scanner.nextLine(); 
            while (true) {  
                System.out.print("Votre angle est en (1) degrés ou (2) radians ? : ");
                String choice = scanner.nextLine().trim();
                if (choice.equals("1")) {
                    System.out.print("Entrez l'angle en degrés : ");
                    double angleDeg = scanner.nextDouble();
                    scanner.nextLine(); 
                    double angleRad = convertirDegreEnRadianEtAfficher(angleDeg);  
                    resultatCos = Math.cos(angleRad);
                    if (Math.abs(resultatCos) < 1e-10) {
                        resultatCos = 0.0;
                    }
                    System.out.println("Donc on a cos(" + angleDeg + " °) = " + resultatCos);
                    break;  
                } else if (choice.equals("2")) {
                	while (true) {
                    	double result;
                        System.out.print("Choisir un radian : ");
                        String nbr = scanner.nextLine().trim(); 
                        List<String> liste = new ArrayList<>();
                        if (nbr.contains("pi")) {
                            try {
                            	for (int j = 0; j < nbr.length(); j++) {
                                    char c = nbr.charAt(j);
                                    if (c != ' ') {
                                        if (c == 'p' && nbr.charAt(j + 1) == 'i') {
                                            liste.add("pi");
                                            j = j + 1; 
                                        } 
                                        else {
                                            liste.add(String.valueOf(c));
                                        }
                                    }
                                }
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < liste.size(); i++) {
                                		if (liste.get(i).equals("pi") ) {  
                                            sb.append(" pi");
                                        }else if (liste.get(i).equals("/")){
                                            sb.append(" / ");
                                        }else {
                                    		sb.append(liste.get(i));
                                        }
                                	
                                }
                                String expr = sb.toString();
                            
                             StringBuilder exprAvecMul = new StringBuilder();

                             for (int i = 0; i < expr.length(); i++) {
                                 char c = expr.charAt(i);
                               
                                 if (c == 'p') {
                                         exprAvecMul.append('*'); // ajoute '*'
                                     exprAvecMul.append(" pi");
                                     i = i + 1; // sauter le 'i' car déjà ajouté
                                 } else {
                                     exprAvecMul.append(c);
                                 }
                             }

                             String exprFinal = exprAvecMul.toString();
                             String exprAvec180 = exprFinal.replace("pi", "180");
                             StringBuilder exprAvecpo = new StringBuilder();
                             if (exprAvec180.charAt(0) == ' ' && exprAvec180.charAt(1) == '*' ) {
                            	 for (int i = 3; i < exprAvec180.length(); i++) {
                                     char c = exprAvec180.charAt(i);
                                     exprAvecpo.append(c);

                            	 }
                                 String exprFinaly = exprAvecpo.toString();
                                 result = evaluerExpression(exprFinaly);

                             }
                             else {
                                 result = evaluerExpression(exprAvec180);
                             }

                             double angleeRad = convertirDegreEnRadianEtAfficher(result);  
                             resultatCos = Math.cos(angleeRad);
                             if (Math.abs(resultatCos) < 1e-10) {
                            	    resultatCos = 0.0;
                            	}
                             System.out.println("Donc on a cos(" + result + "°) = " + resultatCos);
                                break;
                            } catch (Exception e) {
                                System.out.println("Erreur lors de l'évaluation. Vérifiez la syntaxe de votre expression.");
                            }
                        } 
                        else if(!nbr.contains("pi")){
                        	try {
                            	for (int j = 0; j < nbr.length(); j++) {
                                    char c = nbr.charAt(j);
                                    if (c != ' ') {
                                    	liste.add(String.valueOf(c));
               
                                    }
                                }
                            	
                            	StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < liste.size(); i++) {
                                		String c = liste.get(i);
                                		
                                		if ( "+-/*".contains(c)) {  
                                            sb.append(" "+c+" ");
                           
                                        }else {
                                    		sb.append(liste.get(i));
                                        }
                                	
                                }
                        	    String expression = sb.toString();
                        	    result = evaluerExpression(expression);
                                int den = 314;
                                int num = (int) Math.round(result * 100);
                                int pgcd = pgcd(num, den);
                                num /= pgcd;
                                den /= pgcd;
                                String exprAveccPi;
                                String exprAvepi = "";
                                if (den == 1) {
                                	exprAveccPi = (num == 1 ? "pi" : num + " pi");
                                	exprAvepi = (num == 1 ? "pi" : num + " * pi");
                                } else if (num == 1) {
                                	exprAveccPi = "pi / " + den;
                                } else {
                                	exprAveccPi = num + " pi / " + den;
                                	exprAvepi = num + " * pi / " + den;
                                }
                                System.out.println("Valeur en radian est : " + exprAveccPi );
                          
                                
                                String exprAvec180 = exprAvepi.replace("pi", "180");
                                result = evaluerExpression(exprAvec180);
                                double angleeRad = convertirDegreEnRadianEtAfficher(result);  
                                resultatCos = Math.cos(angleeRad);
                                if (Math.abs(resultatCos) < 1e-10) {
                                    resultatCos = 0.0;
                                }
                                System.out.println("Donc on a cos(" + exprAveccPi + " rad/s) = " + resultatCos);
                                break;
                                
                            } catch (Exception e) {
                                System.out.println("Erreur lors de l'évaluation. Vérifiez la syntaxe de votre expression.");
                            }
                        }
               
                    
                }


                    break;
                } else {
                    System.out.println("Choix invalide. Veuillez recommencer.");
                }
            }
        }
        if (rep.equals("5")) {
            double resultatSin = 0.0;
            scanner.nextLine(); 
            while (true) {  
                System.out.print("Votre angle est en (1) degrés ou (2) radians ? : ");
                String choice = scanner.nextLine().trim();
                if (choice.equals("1")) {
                    System.out.print("Entrez l'angle en degrés : ");
                    double angleDeg = scanner.nextDouble();
                    scanner.nextLine(); 
                    double angleRad = convertirDegreEnRadianEtAfficher(angleDeg);  
                    resultatSin = Math.sin(angleRad);
                    if (Math.abs(resultatSin) < 1e-10) {
                        resultatSin = 0.0;
                    }
                    System.out.println("Donc on a sin(" + angleDeg + " °) = " + resultatSin);
                    break;  
                } else if (choice.equals("2")) {
                    while (true) {
                        double result;
                        System.out.print("Choisir un radian : ");
                        String nbr = scanner.nextLine().trim(); 
                        List<String> liste = new ArrayList<>();
                        if (nbr.contains("pi")) {
                            try {
                                for (int j = 0; j < nbr.length(); j++) {
                                    char c = nbr.charAt(j);
                                    if (c != ' ') {
                                        if (c == 'p' && nbr.charAt(j + 1) == 'i') {
                                            liste.add("pi");
                                            j = j + 1; 
                                        } else {
                                            liste.add(String.valueOf(c));
                                        }
                                    }
                                }
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < liste.size(); i++) {
                                    if (liste.get(i).equals("pi")) {
                                        sb.append(" pi");
                                    } else if (liste.get(i).equals("/")) {
                                        sb.append(" / ");
                                    } else {
                                        sb.append(liste.get(i));
                                    }
                                }
                                String expr = sb.toString();
                                StringBuilder exprAvecMul = new StringBuilder();
                                for (int i = 0; i < expr.length(); i++) {
                                    char c = expr.charAt(i);
                                    if (c == 'p') {
                                        exprAvecMul.append('*');
                                        exprAvecMul.append(" pi");
                                        i = i + 1;
                                    } else {
                                        exprAvecMul.append(c);
                                    }
                                }

                                String exprFinal = exprAvecMul.toString();
                                String exprAvec180 = exprFinal.replace("pi", "180");
                                StringBuilder exprAvecpo = new StringBuilder();
                                if (exprAvec180.charAt(0) == ' ' && exprAvec180.charAt(1) == '*') {
                                    for (int i = 3; i < exprAvec180.length(); i++) {
                                        char c = exprAvec180.charAt(i);
                                        exprAvecpo.append(c);
                                    }
                                    String exprFinaly = exprAvecpo.toString();
                                    result = evaluerExpression(exprFinaly);
                                } else {
                                    result = evaluerExpression(exprAvec180);
                                }

                                double angleeRad = convertirDegreEnRadianEtAfficher(result);  
                                resultatSin = Math.sin(angleeRad);
                                if (Math.abs(resultatSin) < 1e-10) {
                                    resultatSin = 0.0;
                                }
                                System.out.println("Donc on a sin(" + result + "°) = " + resultatSin);
                                break;
                            } catch (Exception e) {
                                System.out.println("Erreur lors de l'évaluation. Vérifiez la syntaxe de votre expression.");
                            }
                        } else if (!nbr.contains("pi")) {
                            try {
                                for (int j = 0; j < nbr.length(); j++) {
                                    char c = nbr.charAt(j);
                                    if (c != ' ') {
                                        liste.add(String.valueOf(c));
                                    }
                                }

                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < liste.size(); i++) {
                                    String c = liste.get(i);
                                    if ("+-/*".contains(c)) {
                                        sb.append(" ").append(c).append(" ");
                                    } else {
                                        sb.append(c);
                                    }
                                }

                                String expression = sb.toString();
                                result = evaluerExpression(expression);
                                int den = 314;
                                int num = (int) Math.round(result * 100);
                                int pgcd = pgcd(num, den);
                                num /= pgcd;
                                den /= pgcd;
                                String exprAveccPi;
                                String exprAvepi = "";
                                if (den == 1) {
                                    exprAveccPi = (num == 1 ? "pi" : num + " pi");
                                    exprAvepi = (num == 1 ? "pi" : num + " * pi");
                                } else if (num == 1) {
                                    exprAveccPi = "pi / " + den;
                                    exprAvepi = "pi / " + den;
                                } else {
                                    exprAveccPi = num + " pi / " + den;
                                    exprAvepi = num + " * pi / " + den;
                                }
                                System.out.println("Valeur en radian est : " + exprAveccPi);

                                String exprAvec180 = exprAvepi.replace("pi", "180");
                                result = evaluerExpression(exprAvec180);
                                double angleeRad = convertirDegreEnRadianEtAfficher(result);  
                                resultatSin = Math.sin(angleeRad);
                                if (Math.abs(resultatSin) < 1e-10) {
                                    resultatSin = 0.0;
                                }
                                System.out.println("Donc on a sin(" + exprAveccPi + " rad/s) = " + resultatSin);
                                break;
                            } catch (Exception e) {
                                System.out.println("Erreur lors de l'évaluation. Vérifiez la syntaxe de votre expression.");
                            }
                        }
                    }

                    break;
                } else {
                    System.out.println("Choix invalide. Veuillez recommencer.");
                }
            }
        }
        if (rep.equals("6")) {
            double resultatTan = 0.0;
            scanner.nextLine(); 
            while (true) {  
                System.out.print("Votre angle est en (1) degrés ou (2) radians ? : ");
                String choice = scanner.nextLine().trim();
                if (choice.equals("1")) {
                    System.out.print("Entrez l'angle en degrés : ");
                    double angleDeg = scanner.nextDouble();
                    scanner.nextLine(); 
                    while(true) {
                    	if (angleDeg != 90) {
                    		break;
                    	}
                    	else {
                    		System.out.print("erreur tan(90 °) est impossible .redonner l'angle :");
                    		angleDeg = scanner.nextDouble();
                    		scanner.nextLine();
                    	}
                    }
                    
                    double angleRad = convertirDegreEnRadianEtAfficher(angleDeg);  
                    resultatTan = Math.tan(angleRad);
                    if (Math.abs(resultatTan) < 1e-10) {
                        resultatTan = 0.0;
                    }
                    System.out.println("Donc on a tan(" + angleDeg + " °) = " + resultatTan);
                    break;  
                } else if (choice.equals("2")) {
                    while (true) {
                        double result;
                        System.out.print("Choisir un radian : ");
                        String nbr = scanner.nextLine().trim(); 
                        List<String> liste = new ArrayList<>();
                        if (nbr.contains("pi")) {
                            try {
                                for (int j = 0; j < nbr.length(); j++) {
                                    char c = nbr.charAt(j);
                                    if (c != ' ') {
                                        if (c == 'p' && nbr.charAt(j + 1) == 'i') {
                                            liste.add("pi");
                                            j = j + 1; 
                                        } else {
                                            liste.add(String.valueOf(c));
                                        }
                                    }
                                }
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < liste.size(); i++) {
                                    if (liste.get(i).equals("pi")) {
                                        sb.append(" pi");
                                    } else if (liste.get(i).equals("/")) {
                                        sb.append(" / ");
                                    } else {
                                        sb.append(liste.get(i));
                                    }
                                }
                                String expr = sb.toString();
                                StringBuilder exprAvecMul = new StringBuilder();
                                for (int i = 0; i < expr.length(); i++) {
                                    char c = expr.charAt(i);
                                    if (c == 'p') {
                                        exprAvecMul.append('*');
                                        exprAvecMul.append(" pi");
                                        i = i + 1;
                                    } else {
                                        exprAvecMul.append(c);
                                    }
                                }

                                String exprFinal = exprAvecMul.toString();
                                String exprAvec180 = exprFinal.replace("pi", "180");
                                StringBuilder exprAvecpo = new StringBuilder();
                                if (exprAvec180.charAt(0) == ' ' && exprAvec180.charAt(1) == '*') {
                                    for (int i = 3; i < exprAvec180.length(); i++) {
                                        char c = exprAvec180.charAt(i);
                                        exprAvecpo.append(c);
                                    }
                                    String exprFinaly = exprAvecpo.toString();
                                    result = evaluerExpression(exprFinaly);
                                } else {
                                    result = evaluerExpression(exprAvec180);
                                }

                                double angleeRad = convertirDegreEnRadianEtAfficher(result);

                                // ✅ CONDITION MATHÉMATIQUE : tan(x) indéfini si cos(x) = 0
                                if (Math.abs(Math.cos(angleeRad)) < 1e-10) {
                                    System.out.println("Erreur : tan(" + result + "°) est indéfini. Redonnez un angle valide.");
                                    continue; // recommencer la boucle
                                }

                                resultatTan = Math.tan(angleeRad);
                                if (Math.abs(resultatTan) < 1e-10) {
                                    resultatTan = 0.0;
                                }
                                System.out.println("Donc on a tan(" + result + "°) = " + resultatTan);
                                break;
                            } catch (Exception e) {
                                System.out.println("Erreur lors de l'évaluation. Vérifiez la syntaxe de votre expression.");
                            }
                        } else if (!nbr.contains("pi")) {
                            try {
                                for (int j = 0; j < nbr.length(); j++) {
                                    char c = nbr.charAt(j);
                                    if (c != ' ') {
                                        liste.add(String.valueOf(c));
                                    }
                                }

                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < liste.size(); i++) {
                                    String c = liste.get(i);
                                    if ("+-/*".contains(c)) {
                                        sb.append(" ").append(c).append(" ");
                                    } else {
                                        sb.append(c);
                                    }
                                }

                                String expression = sb.toString();
                                result = evaluerExpression(expression);
                                int den = 314;
                                int num = (int) Math.round(result * 100);
                                int pgcd = pgcd(num, den);
                                num /= pgcd;
                                den /= pgcd;
                                String exprAveccPi;
                                String exprAvepi = "";
                                if (den == 1) {
                                    exprAveccPi = (num == 1 ? "pi" : num + " pi");
                                    exprAvepi = (num == 1 ? "pi" : num + " * pi");
                                } else if (num == 1) {
                                    exprAveccPi = "pi / " + den;
                                    exprAvepi = "pi / " + den;
                                } else {
                                    exprAveccPi = num + " pi / " + den;
                                    exprAvepi = num + " * pi / " + den;
                                }
                                System.out.println("Valeur en radian est : " + exprAveccPi);

                                String exprAvec180 = exprAvepi.replace("pi", "180");
                                result = evaluerExpression(exprAvec180);
                                double angleeRad = convertirDegreEnRadianEtAfficher(result);

                                // ✅ Même vérification ici
                                if (Math.abs(Math.cos(angleeRad)) < 1e-10) {
                                    System.out.println("Erreur : tan(" + exprAveccPi + ") est indéfini. Redonnez un angle valide.");
                                    continue;
                                }

                                resultatTan = Math.tan(angleeRad);
                                if (Math.abs(resultatTan) < 1e-10) {
                                    resultatTan = 0.0;
                                }
                                System.out.println("Donc on a tan(" + exprAveccPi + " rad/s) = " + resultatTan);
                                break;
                            } catch (Exception e) {
                                System.out.println("Erreur lors de l'évaluation. Vérifiez la syntaxe de votre expression.");
                            }
                        }
                    }
                
                break;  
 
            }else {
                    System.out.println("Choix invalide. Veuillez recommencer.");
                }
            }
        }
        if (!continuer) {
        	System.out.println("Voulez-vous recommencer ? Taper oui ou non.");
        	String reponse = "";
        	reponse = scanner.next();
        	while (true) {
        		if (reponse.equals("oui") || reponse.equals("non")) {
        			break;
        		}else {
        			System.out.println("la reponse est incorrecte ,retaper.");
        			reponse = scanner.next();
        		}
        	}
        	if (reponse.equals("oui")) {
        		continuer = true;
        	}else {
        		System.out.println("merci ,au revoir");
        		break;
        	}
        }
    	}
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Calculatrice scientifique ou opérations basiques ?");
        System.out.println("Tapez 1 pour opérations basiques.");
        System.out.println("Tapez 2 pour calculatrice scientifique.");
        String m ; 
    	int tap = 0;
    	while (true) {
            System.out.print("Taper 1 ou 2 : ");
            m = scanner.nextLine().trim();

            if (m.equals("1") || m.equals("2")) {
                tap = Integer.parseInt(m);
                break;
            } else {
                System.out.println("❌ Entrée invalide. Veuillez taper 1 ou 2.");
            }
        }
        if (tap == 1) {
            executercalculatrice(scanner);
        } else if (tap == 2) {
            calculatricescientifique(scanner);
        } 

        scanner.close();
    }
}
