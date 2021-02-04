package inf112.skeleton.app.mechanics.map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map implements ApplicationListener {

    TiledMap tiledMap;
    TiledMapTileLayer playerLayer;
    TiledMapTileLayer boardLayer;
    TiledMapTileLayer holeLayer;
    TiledMapTileLayer flagLayer;

    TmxMapLoader tmxMapLoader;

    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    String mapName;

    public Map(String mapName) {
        this.mapName = mapName;
        create();
    }

    @Override
    public void create() {
        tmxMapLoader = new TmxMapLoader();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 5, 5);

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);
        renderer.setView(camera);

        tiledMap = tmxMapLoader.load(mapName);

        boardLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");
    }

    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
