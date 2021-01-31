package pl.ski.picture;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPictureRepository extends PagingAndSortingRepository<Picture, Long> {
}
