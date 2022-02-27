package com.example.application.models;

import java.util.List;

public class TVSeriesLatestModel {

    /**
     * backdrop_path : null
     * created_by : []
     * episode_run_time : []
     * first_air_date : 2020-11-27
     * genres : [{"id":10764,"name":"Reality"}]
     * homepage :
     * id : 113568
     * in_production : true
     * languages : []
     * last_air_date : null
     * last_episode_to_air : null
     * name : Familiekokkene
     * next_episode_to_air : {"air_date":"2020-11-27","episode_number":1,"id":2523267,"name":"","overview":"","production_code":"","season_number":1,"still_path":null,"vote_average":0,"vote_count":0}
     * networks : [{"name":"NRK1","id":379,"logo_path":"/4TTFfXnGIEosh3EPYX25ERWp1XG.png","origin_country":"NO"}]
     * number_of_episodes : 7
     * number_of_seasons : 1
     * origin_country : ["NO"]
     * original_language : no
     * original_name : Familiekokkene
     * overview :
     * popularity : 0
     * poster_path : null
     * production_companies : []
     * production_countries : [{"iso_3166_1":"NO","name":"Norway"}]
     * seasons : [{"air_date":"2020-11-27","episode_count":7,"id":169776,"name":"Season 1","overview":"","poster_path":null,"season_number":1}]
     * spoken_languages : []
     * status : Returning Series
     * tagline :
     * type : Reality
     * vote_average : 0
     * vote_count : 0
     */

    private Object backdrop_path;
    private String first_air_date;
    private String homepage;
    private int id;
    private boolean in_production;
    private Object last_air_date;
    private Object last_episode_to_air;
    private String name;
    private NextEpisodeToAirBean next_episode_to_air;
    private int number_of_episodes;
    private int number_of_seasons;
    private String original_language;
    private String original_name;
    private String overview;
    private int popularity;
    private Object poster_path;
    private String status;
    private String tagline;
    private String type;
    private int vote_average;
    private int vote_count;
    private List<?> created_by;
    private List<?> episode_run_time;
    private List<GenresBean> genres;
    private List<?> languages;
    private List<NetworksBean> networks;
    private List<String> origin_country;
    private List<?> production_companies;
    private List<ProductionCountriesBean> production_countries;
    private List<SeasonsBean> seasons;
    private List<?> spoken_languages;

    public Object getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(Object backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public Object getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(Object last_air_date) {
        this.last_air_date = last_air_date;
    }

    public Object getLast_episode_to_air() {
        return last_episode_to_air;
    }

    public void setLast_episode_to_air(Object last_episode_to_air) {
        this.last_episode_to_air = last_episode_to_air;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NextEpisodeToAirBean getNext_episode_to_air() {
        return next_episode_to_air;
    }

    public void setNext_episode_to_air(NextEpisodeToAirBean next_episode_to_air) {
        this.next_episode_to_air = next_episode_to_air;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Object getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(Object poster_path) {
        this.poster_path = poster_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public List<?> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(List<?> created_by) {
        this.created_by = created_by;
    }

    public List<?> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(List<?> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public List<?> getLanguages() {
        return languages;
    }

    public void setLanguages(List<?> languages) {
        this.languages = languages;
    }

    public List<NetworksBean> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworksBean> networks) {
        this.networks = networks;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public List<?> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<?> production_companies) {
        this.production_companies = production_companies;
    }

    public List<ProductionCountriesBean> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<ProductionCountriesBean> production_countries) {
        this.production_countries = production_countries;
    }

    public List<SeasonsBean> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsBean> seasons) {
        this.seasons = seasons;
    }

    public List<?> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<?> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public static class NextEpisodeToAirBean {
        /**
         * air_date : 2020-11-27
         * episode_number : 1
         * id : 2523267
         * name :
         * overview :
         * production_code :
         * season_number : 1
         * still_path : null
         * vote_average : 0
         * vote_count : 0
         */

        private String air_date;
        private int episode_number;
        private int id;
        private String name;
        private String overview;
        private String production_code;
        private int season_number;
        private Object still_path;
        private int vote_average;
        private int vote_count;

        public String getAir_date() {
            return air_date;
        }

        public void setAir_date(String air_date) {
            this.air_date = air_date;
        }

        public int getEpisode_number() {
            return episode_number;
        }

        public void setEpisode_number(int episode_number) {
            this.episode_number = episode_number;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getProduction_code() {
            return production_code;
        }

        public void setProduction_code(String production_code) {
            this.production_code = production_code;
        }

        public int getSeason_number() {
            return season_number;
        }

        public void setSeason_number(int season_number) {
            this.season_number = season_number;
        }

        public Object getStill_path() {
            return still_path;
        }

        public void setStill_path(Object still_path) {
            this.still_path = still_path;
        }

        public int getVote_average() {
            return vote_average;
        }

        public void setVote_average(int vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }
    }

    public static class GenresBean {
        /**
         * id : 10764
         * name : Reality
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class NetworksBean {
        /**
         * name : NRK1
         * id : 379
         * logo_path : /4TTFfXnGIEosh3EPYX25ERWp1XG.png
         * origin_country : NO
         */

        private String name;
        private int id;
        private String logo_path;
        private String origin_country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(String logo_path) {
            this.logo_path = logo_path;
        }

        public String getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(String origin_country) {
            this.origin_country = origin_country;
        }
    }

    public static class ProductionCountriesBean {
        /**
         * iso_3166_1 : NO
         * name : Norway
         */

        private String iso_3166_1;
        private String name;

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SeasonsBean {
        /**
         * air_date : 2020-11-27
         * episode_count : 7
         * id : 169776
         * name : Season 1
         * overview :
         * poster_path : null
         * season_number : 1
         */

        private String air_date;
        private int episode_count;
        private int id;
        private String name;
        private String overview;
        private Object poster_path;
        private int season_number;

        public String getAir_date() {
            return air_date;
        }

        public void setAir_date(String air_date) {
            this.air_date = air_date;
        }

        public int getEpisode_count() {
            return episode_count;
        }

        public void setEpisode_count(int episode_count) {
            this.episode_count = episode_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public Object getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(Object poster_path) {
            this.poster_path = poster_path;
        }

        public int getSeason_number() {
            return season_number;
        }

        public void setSeason_number(int season_number) {
            this.season_number = season_number;
        }
    }
}
