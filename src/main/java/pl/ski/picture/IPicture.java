package pl.ski.picture;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPicture extends PagingAndSortingRepository<Picture, Long> {
}
