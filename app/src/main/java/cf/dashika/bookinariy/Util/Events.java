package cf.dashika.bookinariy.Util;

import cf.dashika.bookinariy.Model.Volumes;

/**
 * Created by dashika on 26/03/17.
 */

public class Events {

    private Events() {
    }

    public static class setVolumes {
        public final Volumes volumes;

        public setVolumes(Volumes volumes) {
            this.volumes = volumes;
        }
    }

    public static class refresh {
        public refresh() {

        }
    }

}
