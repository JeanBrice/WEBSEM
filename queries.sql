# QUERIES

-- Get all scenes and the film name associated to it
PREFIX :<http://www.semanticweb.org/localuser/ontologies/2018/4/untitled-ontology-5/>
SELECT ?title ?scene ?lat ?long
WHERE{
  ?scene :takesPlaceIn :New_York .
  ?film :contains ?scene .
  ?film :has_for_title ?title .
  ?scene :has_longitude ?long .
  ?scene :has_latitude ?lat
}

-- Get all films with specific name
PREFIX :<http://www.semanticweb.org/localuser/ontologies/2018/4/untitled-ontology-5/>
SELECT ?film ?year ?genre ?country ?metascore ?imdb ?link ?poster ?runtime
WHERE{
  ?film :has_for_title "Title" .
  ?film :has_for_year ?year .
  ?film :has_for_genre ?genre .
  ?film :has_for_country ?country .
  ?film :has_for_metascore ?metascore .
  ?film :has_for_imdb_rating ?imdb .
  ?film :has_for_imdb_link ?link .
  ?film :has_for_poster ?poster .
  ?film :has_for_runtime ?runtime
}