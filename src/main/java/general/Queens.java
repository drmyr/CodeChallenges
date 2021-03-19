package general;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class Queens {

    public List<int[]> findNQueenCoords(final int n) {
        if(n == 1) return singletonList(new int[] {0, 0});
        if(n == 2 || n == 3) return emptyList();

        class Queen {
            final int row;
            int col = -1;

            Queen(final int id) {
                this.row = id;
            }

            void advance() {
                if((col + 1) < n) {
                    col++;
                }
            }

            boolean hasAnotherMove() {
                return this.col < (n - 1);
            }

            void removeFromBoard() {
                this.col = -1;
            }

            boolean isOnTheBoard() {
                return this.col > 0 && this.col < (n - 1);
            }

            int[] getPosition() {
                return new int[] {this.row, this.col};
            }

            public boolean canBeAttacked(final List<Queen> others) {
                for(final Queen other : others) {
                    if(this.row == other.row || !other.isOnTheBoard()) continue;
                    if(other.col == this.col ||
                        (this.row - this.col) == (other.row - other.col) ||
                        (this.row + this.col) == (other.row + other.col)) return true;
                }
                return false;
            }
        }

        final List<Queen> queens = new ArrayList<>();
        for(int i = 0; i < n; i++) queens.add(new Queen(i));

        Queen currentQueen = queens.get(0);
        while(true) {
            currentQueen.advance();
            if(!currentQueen.canBeAttacked(queens)) {
                if(currentQueen.row == (n - 1)) return queens.stream().map(Queen::getPosition).collect(toList());
                currentQueen = queens.get(currentQueen.row + 1);
            } else {
                while(!currentQueen.hasAnotherMove()) {
                    currentQueen.removeFromBoard();
                    currentQueen = queens.get(currentQueen.row - 1);
                }
            }
        }
    }
}
