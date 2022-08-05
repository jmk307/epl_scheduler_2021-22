package com.football.epl_scheduler_202122.repository.Board;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.dto.Board.Search.SearchCondition;
import com.football.epl_scheduler_202122.dto.Board.Search.SearchType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Supplier;

import static com.football.epl_scheduler_202122.domain.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepository {

    public final JPAQueryFactory queryFactory;

    public List<Board> search(SearchCondition condition, String startDate, Pageable pageable) {
        return queryFactory
                .selectFrom(board)
                .where(isSearchable(condition.getType(), condition.getContent()).and(board.startDate.eq(startDate)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    BooleanBuilder homeCt(String content) {
        return nullSafeBuilder(() -> board.home.contains(content));
    }

    BooleanBuilder awayCt(String content) {
        return nullSafeBuilder(() -> board.away.contains(content));
    }

    BooleanBuilder isSearchable(SearchType sType, String content) {
        if (sType == SearchType.HOME) {
            return homeCt(content);
        }
        else if (sType == SearchType.AWAY) {
            return awayCt(content);
        }
        else {
            return homeCt(content).or(awayCt(content));
        }
    }
}
