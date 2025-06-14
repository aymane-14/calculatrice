import java.util.Scanner;
public class calculatrice {
    public static void executercalculatrice() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            double resultat = 0;
            String operateur = "";
            boolean premier = true;
            String reponse = "";
            boolean deuxieme = true;
            boolean troixieme = true;
            String rp = "";
            while (true) {
                double nombre;
                if (premier) {
                    while (true) {
                        System.out.print("Entrez un nombre : ");
                        String input = scanner.next();  

                        try {
                            resultat = Double.parseDouble(input);  
                            break;
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
                    char op = operateur.charAt(0);
                    String ope = op  + "";
                    while (!"+-*/=".contains(ope)) {
                    	System.out.println("l'operateur est incorrecte .");
                    	System.out.print("redonnez l'opérateur ; ");
                        operateur = scanner.next();
                        op = operateur.charAt(0);
                        ope = op  + "";
                    }

                    if (operateur.equals("=")) {
                        System.out.println("Résultat final : " + resultat);
                        System.out.println("voulez-vous répéter ou non ?");
                        while (true) {
                            rp = scanner.next();
                            if (rp.equals("oui")) {
                            	System.out.println("recommencer ; ");
                            	premier = true;
                            	deuxieme = false ;
                            	break;
                            }
                            else if (rp.equals("non")){
                            	System.out.println("merci au revoir .");
                            	continuer = false;
                            	break;
                            }
                            else {
                            	System.out.println("tu as tapé une fausse réponse.Refaire.");
                            }
                        }
                        break;
                    }
                }
                if (deuxieme) {
                	while (true) {
                        System.out.print("Entrez un autre nombre : ");
                        String input = scanner.next();  

                        try {
                            nombre = Double.parseDouble(input);  
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Ce n'est pas un nombre valide. Réessayez.");
                        }
                    }
                    troixieme = true;

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
                                    reponse = scanner.next();
                                    if (reponse.equals("d")) {
                                    	deuxieme = true;
                                    	troixieme = false;
                                    	break;
                                    }
                                    else if (reponse.equals("r")){
                                    	System.out.println("refaire l'opération.");
                                    	premier = true;
                                    	troixieme = true;
                                    	break;
                                    }
                                    else {
                                    	System.out.println("tu as tapé une fausse réponse.Refaire.");
                                    }
                                }
                                
                            }
                            break;
                    }
                	
                }
                
            }
        }

    scanner.close();
    }

    public static void main(String[] args) {
        executercalculatrice(); 
    }
}
