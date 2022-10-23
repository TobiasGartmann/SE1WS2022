package org.hbrs.se1.ws22.uebung3;
public class Client {
    public void viewContainer() {
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        Member m3 = new ConcreteMember(3);
        Member m4 = new ConcreteMember(4);

        Container con = Container.getInstance();

        try {
            con.addMember(m1);
            con.addMember(m2);
            con.addMember(m3);
            con.addMember(m4);
        } catch (ContainerException e) {
            System.out.println(e.getMessage());
        }

        MemberView memView = new MemberView();
        memView.dump(con.getCurrentList());
    }
}
