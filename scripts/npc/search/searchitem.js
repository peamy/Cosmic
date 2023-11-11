var status;

function start() {
    status = -1;
    //action(1, 0, 0);
    cm.sendSimple(cm.getPlayer().SearchResult)
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && type > 0) {
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        cm.gainItem(selection);

        if (status == 0) {
            cm.dispose();
        }
    }
}