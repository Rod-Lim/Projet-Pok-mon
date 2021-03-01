package tp13;

import java.util.Random;

public class AttaquePhysique extends Attaque {

	public AttaquePhysique(String nom, int puissance, int precision, int nombreRepetitions) {
		super(nom, puissance, precision, nombreRepetitions);
	}

	@Override
	protected void utiliserAttaque(Pokemon attaquant, Pokemon victime) {
		Random random = new Random();
		int i=random.nextInt(101);
		if (null != attaquant && null != victime) {
			if (this.repetitionsRestantes >= 1) {
				int randomAttaquant = random.nextInt(attaquant.getNiveau()+1);
				int randomVictime = random.nextInt(victime.getNiveau()+1);
				if ((attaquant.getAttaque()+randomAttaquant) > (victime.getDefense()+randomVictime) && i < this.precision) {
					int dommages=random.nextInt(this.puissance+1);
					victime.blessure(dommages);
				} else {
					System.out.println(victime.getNom() + " a esquivé l'attaque !");
				}
				this.baisserNombreRepetitions();
			} 
		}
	}

	@Override
	protected boolean isCompatible(Pokemon pokemon) {
		if (null==pokemon) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public AttaquePhysique genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}

}
